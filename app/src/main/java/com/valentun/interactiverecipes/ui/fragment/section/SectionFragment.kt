package com.valentun.interactiverecipes.ui.fragment.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.valentun.interactiverecipes.R
import com.valentun.interactiverecipes.data.pojo.Section
import com.valentun.interactiverecipes.presentation.presenter.section.SectionPresenter
import com.valentun.interactiverecipes.presentation.view.section.SectionView
import com.valentun.interactiverecipes.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_section.*

private const val EXTRA_SECTION = "EXTRA_SECTION"
private const val EXTRA_LAST_PAGE = "EXTRA_LAST_PAGE"

class SectionFragment : BaseFragment(), SectionView {
    companion object {

        fun newInstance(section: Section, isLastPage: Boolean): SectionFragment {
            val fragment = SectionFragment()

            val args: Bundle = bundleOf(
                EXTRA_SECTION to section,
                EXTRA_LAST_PAGE to isLastPage
            )

            fragment.arguments = args

            return fragment
        }
    }

    @InjectPresenter
    lateinit var presenter: SectionPresenter

    @ProvidePresenter
    fun providePresenter() = SectionPresenter(section, isLastPage)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_section, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionNext.setOnClickListener { presenter.nextClicked() }
    }

    override fun showSection(section: Section, isLastPage: Boolean) {
        testText.text = section.name

        val buttonTextRes = if (isLastPage) R.string.section_finish else R.string.section_next

        sectionNext.setText(buttonTextRes)
    }

    private val section: Section
        get() = arguments!![EXTRA_SECTION] as Section

    private val isLastPage: Boolean
        get() = arguments!![EXTRA_LAST_PAGE] as Boolean
}
