package com.valentun.interactiverecipes.presentation.presenter.section

import com.arellomobile.mvp.InjectViewState
import com.valentun.interactiverecipes.data.pojo.Section
import com.valentun.interactiverecipes.presentation.presenter.BasePresenter
import com.valentun.interactiverecipes.presentation.presenter.cooking.LastPageEvent
import com.valentun.interactiverecipes.presentation.presenter.cooking.NextPageEvent
import com.valentun.interactiverecipes.presentation.view.section.SectionView

@InjectViewState
class SectionPresenter(private val section: Section,
                       private val lastPage: Boolean) : BasePresenter<SectionView>() {
    init {
        viewState.showSection(section, lastPage)
    }

    fun nextClicked() {
        if (lastPage) {
            eventBus.post(LastPageEvent())
        } else {
            eventBus.post(NextPageEvent())
        }
    }
}
