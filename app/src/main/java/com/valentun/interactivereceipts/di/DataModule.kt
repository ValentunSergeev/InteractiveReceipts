package com.valentun.interactivereceipts.di

import com.valentun.interactivereceipts.data.Repository
import com.valentun.interactivereceipts.data.actual.ActualRepository
import com.valentun.interactivereceipts.data.local.LocalDataManager
import org.koin.dsl.module.module


@Suppress("RemoveExplicitTypeArguments")
val dataModule = module {
    single<Repository> { ActualRepository(api = get(), localManager = get()) }

    single<LocalDataManager> { LocalDataManager(get()) }
}
