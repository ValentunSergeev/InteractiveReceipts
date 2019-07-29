package com.valentun.interactiverecipes.presentation.view.cooking

import com.valentun.interactiverecipes.data.pojo.Recipe
import com.valentun.interactiverecipes.presentation.view.ProgressView

interface CookingView : ProgressView {
    fun showRecipe(recipe: Recipe)
    fun nextPage(newSectionPosition: Int)
}
