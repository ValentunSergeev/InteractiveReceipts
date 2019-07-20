package com.valentun.interactiverecipes.ui.fragment.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.valentun.interactiverecipes.R
import com.valentun.interactiverecipes.presentation.presenter.recipes.RecipesPresenter
import com.valentun.interactiverecipes.presentation.view.recipes.RecipesView
import com.valentun.interactiverecipes.ui.fragment.BaseFragment

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
    }
}
