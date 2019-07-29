package com.valentun.interactiverecipes.presentation.view.recipes

import com.valentun.interactiverecipes.data.pojo.RecipeOverview
import com.valentun.interactiverecipes.presentation.view.ProgressView

interface RecipesView : ProgressView {
    fun showRecipes(recipes: List<RecipeOverview>)
}
