@file:Suppress("unused", "RedundantVisibilityModifier")

package com.valentun.interactiverecipes.presentation.presenter.cooking

import com.arellomobile.mvp.InjectViewState
import com.valentun.interactiverecipes.presentation.presenter.BasePresenter
import com.valentun.interactiverecipes.presentation.view.cooking.CookingView
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LastPageEvent

class NextPageEvent

@InjectViewState
class CookingPresenter(private val recipeId: Long) : BasePresenter<CookingView>() {
    init {
        loadRecipe()

        registerToBus()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onNextPageEvent(event: NextPageEvent) {
        viewState.nextPage()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onLastPageEvent(event: LastPageEvent) {
        router.exit()
    }

    private fun loadRecipe() {
        launch {
            viewState.showProgress()

            val recipe = repository.getRecipe(recipeId)

            viewState.hideProgress()

            viewState.showRecipe(recipe)
            viewState.setTitle(recipe.title)
        }
    }
}
