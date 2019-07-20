package com.valentun.interactivereceipts.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.valentun.interactivereceipts.data.Repository
import com.valentun.interactivereceipts.presentation.view.BaseView
import com.valentun.interactivereceipts.presentation.view.ProgressView
import com.valentun.interactivereceipts.Screens
import com.valentun.interactivereceipts.di.errorMessage
import com.valentun.interactivereceipts.di.isAuthError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ru.terrakok.cicerone.Router

abstract class BasePresenter<V : BaseView> : MvpPresenter<V>(), KoinComponent {
    protected val repository: Repository by inject()

    protected val router: Router by inject()

    private val defaultCatch: suspend CoroutineScope.(Throwable) -> Unit = {
        viewState.showMessage(it.errorMessage)

        (viewState as? ProgressView)?.hideProgress()
    }

    protected fun launch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                tryBlock()
            } catch (e: Throwable) {
                if (e.isAuthError()) {
                    logout()
                } else {
                    catchBlock(e)
                }
            }
        }
    }

    protected fun logout() {
        repository.clearAuthInfo()

        router.newRootScreen(Screens.LOGIN)
    }

    protected fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {
        launch(tryBlock, defaultCatch)
    }
}