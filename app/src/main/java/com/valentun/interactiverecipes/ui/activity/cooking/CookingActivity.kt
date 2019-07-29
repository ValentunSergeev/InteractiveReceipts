package com.valentun.interactiverecipes.ui.activity.cooking

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.tabs.TabLayoutMediator
import com.valentun.interactiverecipes.R
import com.valentun.interactiverecipes.data.pojo.Recipe
import com.valentun.interactiverecipes.presentation.presenter.cooking.CookingPresenter
import com.valentun.interactiverecipes.presentation.view.cooking.CookingView
import com.valentun.interactiverecipes.ui.activity.BaseActivity
import com.valentun.interactiverecipes.ui.navigation.navigator.LeafNavigator
import kotlinx.android.synthetic.main.activity_cooking.*

const val EXTRA_ID = "EXTRA_ID"

class CookingActivity : BaseActivity(), CookingView {
    @InjectPresenter
    lateinit var presenter: CookingPresenter

    @ProvidePresenter
    fun provideRecipePresenter() = CookingPresenter(getExtraId())

    private fun getExtraId() = intent.getLongExtra(EXTRA_ID, 0)

    override fun provideNavigator() = LeafNavigator(this)

    override fun nextPage() {
        val currentPosition = cookingPager.currentItem

        cookingPager.currentItem = currentPosition + 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cooking)


        cookingPager.isUserInputEnabled = false
    }

    override fun showProgress() {
        cookingContainer.showProgress()
    }

    override fun hideProgress() {
        cookingContainer.hideProgress()
    }

    override fun showRecipe(recipe: Recipe) {
        cookingPager.adapter = SectionAdapter(this, recipe.sections)
    }
}
