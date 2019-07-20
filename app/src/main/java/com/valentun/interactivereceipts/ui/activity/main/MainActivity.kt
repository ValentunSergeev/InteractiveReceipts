package com.valentun.interactivereceipts.ui.activity.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.valentun.interactivereceipts.R
import com.valentun.interactivereceipts.presentation.presenter.mian.MainPresenter
import com.valentun.interactivereceipts.presentation.view.mian.MainView
import com.valentun.interactivereceipts.ui.activity.BaseActivity
import com.valentun.interactivereceipts.ui.navigation.OnBackListener
import com.valentun.interactivereceipts.ui.navigation.navigator.SupportAppNavigator
import ru.terrakok.cicerone.Navigator


class MainActivity : BaseActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val listener = getCurrentBackListener()

        if (listener != null) {
            listener.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun provideNavigator(): Navigator = MainNavigator(this)

    private fun getCurrentBackListener(): OnBackListener? {
        val fragment = supportFragmentManager.findFragmentById(R.id.mainContainer)
        return fragment as? OnBackListener
    }

    class MainNavigator(activity: AppCompatActivity) : SupportAppNavigator(activity, R.id.mainContainer) {
        override fun createActivityIntent(context: Context, screenKey: String, data: Any?) = TODO()

        override fun createFragment(screenKey: String, data: Any?): Fragment = TODO()
    }
}
