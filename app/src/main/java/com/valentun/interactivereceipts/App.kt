package com.valentun.interactivereceipts

import android.app.Application
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.valentun.interactivereceipts.di.dataModule
import com.valentun.interactivereceipts.di.navigationModule
import com.valentun.interactivereceipts.di.networkModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    companion object {
        lateinit var INSTANCE: App

        fun getString(@StringRes stringRes: Int): String {
            return INSTANCE.getString(stringRes)
        }

        fun getColor(@ColorRes colorRes: Int): Int {
            return ContextCompat.getColor(INSTANCE, colorRes)
        }
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        startKoin(this, listOf(navigationModule, networkModule, dataModule))
    }
}