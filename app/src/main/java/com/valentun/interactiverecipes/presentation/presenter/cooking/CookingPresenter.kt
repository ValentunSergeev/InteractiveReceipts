@file:Suppress("unused", "RedundantVisibilityModifier")

package com.valentun.interactiverecipes.presentation.presenter.cooking

import android.speech.SpeechRecognizer
import com.arellomobile.mvp.InjectViewState
import com.valentun.interactiverecipes.data.pojo.Recipe
import com.valentun.interactiverecipes.presentation.presenter.BasePresenter
import com.valentun.interactiverecipes.presentation.view.cooking.CookingView
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LastPageEvent

class NextPageEvent

@InjectViewState
class CookingPresenter(private val recipeId: Long) : BasePresenter<CookingView>() {
    var recipe: Recipe? = null

    var currentPosition: Int = 0

    init {
        loadRecipe()

        registerToBus()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onNextPageEvent(event: NextPageEvent) {
        viewState.nextPage(++currentPosition)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onLastPageEvent(event: LastPageEvent) {
        router.exit()
    }

    private fun loadRecipe() {

        launch {
            viewState.showProgress()

            recipe = repository.getRecipe(recipeId)

            viewState.hideProgress()

            viewState.showRecipe(recipe!!)
            viewState.setTitle(recipe!!.title)
        }
    }

    fun nextRecognized() {
        recipe?.let {
            if (currentPosition + 1 == it.sections.size) {
                router.exit()
            } else {
            viewState.nextPage(++currentPosition) }
        }
    }
}
