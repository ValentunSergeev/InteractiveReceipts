package com.valentun.interactiverecipes.ui.fragment.createRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.valentun.interactiverecipes.R
import com.valentun.interactiverecipes.presentation.view.createRecipe.CreateRecipeView
import com.valentun.interactiverecipes.presentation.presenter.createRecipe.CreateRecipePresenter

import com.arellomobile.mvp.presenter.InjectPresenter
import com.valentun.interactiverecipes.ui.fragment.BaseFragment

class CreateRecipeFragment : BaseFragment(), CreateRecipeView {
    @InjectPresenter
    lateinit var presenter: CreateRecipePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDisplayedTab(R.id.navigationCreate)
    }
}
