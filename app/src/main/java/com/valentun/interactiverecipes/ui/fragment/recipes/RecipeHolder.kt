package com.valentun.interactiverecipes.ui.fragment.recipes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.valentun.interactiverecipes.data.pojo.Recipe
import com.valentun.interactiverecipes.data.pojo.RecipeOverview
import com.valentun.interactiverecipes.extensions.loadImage
import kotlinx.android.extensions.LayoutContainer

import kotlinx.android.synthetic.main.item_recipe.*


class RecipeHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(recipe: RecipeOverview) {
        itemRecipeTitle.text = recipe.title
        itemRecipeDescription.text = recipe.description
//        itemRecipeImage.loadImage(recipe.imageUrl)
    }
}