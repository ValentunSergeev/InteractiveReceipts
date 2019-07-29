package com.valentun.interactiverecipes.ui.activity

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.valentun.interactiverecipes.R
import com.valentun.interactiverecipes.presentation.view.BaseView
import com.valentun.interactiverecipes.ui.mvp.MvpAppCompatActivity
import com.valentun.interactiverecipes.ui.navigation.UpNavigationProvider
import com.valentun.interactiverecipes.utils.permissions.*
import org.jetbrains.anko.contentView
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

abstract class BaseActivity : PermissionActivity(), BaseView, UpNavigationProvider {
    private val navigatorHolder: NavigatorHolder by inject()

    private var navigator: Navigator? = null


    override fun showMessage(message: String) {
        contentView?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun showMessage(messageRes: Int) {
        showMessage(getString(messageRes))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator = provideNavigator()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun setUpNavigationEnabled(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    protected abstract fun provideNavigator(): Navigator
}