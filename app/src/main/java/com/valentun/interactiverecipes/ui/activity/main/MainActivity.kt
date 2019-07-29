package com.valentun.interactiverecipes.ui.activity.main

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.valentun.interactiverecipes.R
import com.valentun.interactiverecipes.Screens
import com.valentun.interactiverecipes.presentation.presenter.main.MainPresenter
import com.valentun.interactiverecipes.presentation.view.mian.MainView
import com.valentun.interactiverecipes.ui.activity.BaseActivity
import com.valentun.interactiverecipes.ui.activity.cooking.CookingActivity
import com.valentun.interactiverecipes.ui.activity.cooking.EXTRA_ID
import com.valentun.interactiverecipes.ui.fragment.createRecipe.CreateRecipeFragment
import com.valentun.interactiverecipes.ui.fragment.recipes.RecipesFragment
import com.valentun.interactiverecipes.ui.navigation.OnBackListener
import com.valentun.interactiverecipes.ui.navigation.TabHolder
import com.valentun.interactiverecipes.ui.navigation.navigator.SupportAppNavigator
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.Internal
import org.jetbrains.anko.intentFor
import ru.terrakok.cicerone.Navigator
import java.lang.IllegalArgumentException


class MainActivity : BaseActivity(), MainView, TabHolder, BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.navigationRecipes -> presenter.recipesClicked()
            R.id.navigationCreate -> presenter.createClicked()
        }

        return false
    }

    override fun setActiveTab(id: Int) {
        bottomNavigation.menu.findItem(id).isChecked = true
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val listener = getCurrentBackListener()

        if (listener != null) {
            listener.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun provideNavigator(): Navigator = MainNavigator(this)

    private fun getCurrentBackListener(): OnBackListener? {
        val fragment = supportFragmentManager.findFragmentById(R.id.mainContainer)
        return fragment as? OnBackListener
    }

    class MainNavigator(activity: AppCompatActivity) : SupportAppNavigator(activity, R.id.mainContainer) {
        override fun createActivityIntent(context: Context, screenKey: String, data: Any?) =
            when(screenKey) {
                Screens.COOKING -> context.intentFor<CookingActivity>(EXTRA_ID to data)
                else -> null
            }

        override fun createFragment(screenKey: String, data: Any?): Fragment =
                when(screenKey) {
                    Screens.CREATE -> CreateRecipeFragment()
                    Screens.RECIPES -> RecipesFragment()
                    else -> throw IllegalArgumentException("Unknown screen key: $screenKey")
                }
    }
}
