package com.valentun.interactiverecipes.presentation.presenter.main

import com.arellomobile.mvp.InjectViewState
import com.valentun.interactiverecipes.Screens
import com.valentun.interactiverecipes.presentation.presenter.BasePresenter
import com.valentun.interactiverecipes.presentation.view.mian.MainView

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {
    init {
        router.replaceScreen(Screens.RECIPES)
    }

    fun recipesClicked() {
        router.navigateTo(Screens.RECIPES)
    }

    fun createClicked() {
        router.navigateTo(Screens.CREATE)
    }
}
