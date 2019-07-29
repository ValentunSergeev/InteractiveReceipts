package com.valentun.interactiverecipes.presentation.presenter.recipes

import com.arellomobile.mvp.InjectViewState
import com.valentun.interactiverecipes.Screens
import com.valentun.interactiverecipes.data.pojo.RecipeOverview
import com.valentun.interactiverecipes.presentation.presenter.BasePresenter
import com.valentun.interactiverecipes.presentation.view.recipes.RecipesView

@InjectViewState
class RecipesPresenter : BasePresenter<RecipesView>() {

    private var recipes: List<RecipeOverview>? = null

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        launch {
            viewState.showProgress()

            recipes = repository.getRecipes()

            viewState.hideProgress()

            viewState.showRecipes(recipes!!)
        }
    }

    fun itemCLicked(id: Long) {
        router.navigateTo(Screens.COOKING, id)
    }
}
