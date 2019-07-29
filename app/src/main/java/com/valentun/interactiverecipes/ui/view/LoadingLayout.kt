package com.valentun.interactiverecipes.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar

class LoadingLayout : FrameLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        if (childCount > 1) {
            throw IllegalStateException("LoadingLayout can contain only one child")
        }

        addProgress()
    }

    private fun addProgress() {
        val progressView = ProgressBar(context)

        progressView.isIndeterminate = true

        addView(progressView)

        val params = progressView.layoutParams as LayoutParams

        params.height = LayoutParams.WRAP_CONTENT
        params.width = LayoutParams.WRAP_CONTENT
        params.gravity = Gravity.CENTER

        requestLayout()
    }

    fun showProgress() {
        content.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    fun hideProgress() {
        content.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }
}

private val LoadingLayout.content
    get() = getChildAt(1)

private val LoadingLayout.progress
    get() = getChildAt(0)