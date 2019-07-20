package com.valentun.interactiverecipes.di

import com.valentun.interactiverecipes.data.Repository
import com.valentun.interactiverecipes.data.actual.ActualRepository
import com.valentun.interactiverecipes.data.local.LocalDataManager
import org.koin.dsl.module.module


@Suppress("RemoveExplicitTypeArguments")
val dataModule = module {
    single<Repository> { ActualRepository(api = get(), localManager = get()) }

    single<LocalDataManager> { LocalDataManager(get()) }
}
