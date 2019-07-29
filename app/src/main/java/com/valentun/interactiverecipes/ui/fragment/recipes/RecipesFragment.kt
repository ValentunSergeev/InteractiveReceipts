package com.valentun.interactiverecipes.ui.fragment.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.arellomobile.mvp.presenter.InjectPresenter
import com.valentun.interactiverecipes.R
import com.valentun.interactiverecipes.data.pojo.RecipeOverview
import com.valentun.interactiverecipes.presentation.presenter.recipes.RecipesPresenter
import com.valentun.interactiverecipes.presentation.view.recipes.RecipesView
import com.valentun.interactiverecipes.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_recipes.*

class RecipesFragment : BaseFragment(), RecipesView {
    @InjectPresenter
    lateinit var presenter: RecipesPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDisplayedTab(R.id.navigationRecipes)
        recipesList.setHasFixedSize(true)
    }

    override fun showRecipes(recipes: List<RecipeOverview>) {
        setupList(recipes)
    }

    override fun showProgress() {
        recipesContainer.showProgress()
    }

    override fun hideProgress() {
        recipesContainer.hideProgress()
    }

    private fun setupList(recipes: List<RecipeOverview>) {
        val dataSource = dataSourceOf(recipes)
        val layoutManager = LinearLayoutManager(context)

        recipesList.setup {
            withDataSource(dataSource)
            withLayoutManager(layoutManager)

            withItem<RecipeOverview, RecipeHolder>(R.layout.item_recipe) {
                onBind(::RecipeHolder) { _, recipe ->
                    bind(recipe)
                }

                onClick {
                    presenter.itemCLicked(item.id)
                }
            }
        }
    }
}
