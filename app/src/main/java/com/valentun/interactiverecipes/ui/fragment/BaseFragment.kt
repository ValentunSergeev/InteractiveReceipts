package com.valentun.interactiverecipes.ui.fragment

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.valentun.interactiverecipes.presentation.view.BaseView
import com.valentun.interactiverecipes.ui.mvp.MvpAppCompatFragment
import com.valentun.interactiverecipes.ui.navigation.TabHolder
import com.valentun.interactiverecipes.ui.navigation.UpNavigationProvider

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {
    private var snackbar: Snackbar? = null

    private var upNavigationEnabled = false

    override fun showMessage(message: String) {
        view?.let {
            snackbar = Snackbar.make(it, message, Snackbar.LENGTH_LONG)

            snackbar?.show()
        }
    }

    override fun showMessage(messageRes: Int) {
        showMessage(getString(messageRes))
    }

    protected fun setDisplayedTab(id: Int) {
        (activity as? TabHolder)?.setActiveTab(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        snackbar?.dismiss()

        if (upNavigationEnabled) {
            setUpNavigation(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (upNavigationEnabled) {
            setUpNavigation(true)
        }
    }

    override fun setTitle(title: CharSequence) {
        activity?.title = title
    }

    override fun setTitle(messageRes: Int) {
        setTitle(getString(messageRes))
    }

    protected fun enableUpNavigation() {
        setUpNavigation(true)

        upNavigationEnabled = true
    }

    private fun setUpNavigation(enabled: Boolean) {
        (activity as? UpNavigationProvider)?.setUpNavigationEnabled(enabled)
    }
}