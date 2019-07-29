package com.valentun.interactiverecipes.ui.navigation.navigator

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import java.lang.IllegalArgumentException

class LeafNavigator(activity: FragmentActivity) : SupportAppNavigator(activity, 0) {
    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?) = null

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        throw IllegalArgumentException("Leaf navigator supports only back navigation")
    }

}