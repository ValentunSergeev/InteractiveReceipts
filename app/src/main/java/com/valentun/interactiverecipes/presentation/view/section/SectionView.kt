package com.valentun.interactiverecipes.presentation.view.section

import com.valentun.interactiverecipes.data.pojo.Section
import com.valentun.interactiverecipes.presentation.view.BaseView

interface SectionView : BaseView {
    fun showSection(section: Section, isLastPage: Boolean)
}
