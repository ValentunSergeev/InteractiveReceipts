package com.valentun.interactivereceipts.di

import com.valentun.interactivereceipts.App
import com.valentun.interactivereceipts.BuildConfig
import com.valentun.interactivereceipts.R
import retrofit2.HttpException
import java.net.UnknownHostException

val Throwable.errorMessage: String
    get() {
        if (BuildConfig.DEBUG)
            printStackTrace()

        return when (this) {
            is HttpException -> processHttp(this)
            is UnknownHostException -> processUnknownHost()
            else -> message.orEmpty()
        }
    }

fun Throwable.isAuthError() = this is HttpException && this.code() == 401

fun processUnknownHost(): String {
    return App.getString(R.string.error_unable_to_connect)
}

fun processHttp(exception: HttpException): String {
    val resp = exception.response()

    if (resp.code() >= 500) {
        return App.getString(R.string.error_server_unavailable)
    }

    if (resp.code() == 404) {
        return App.getString(R.string.page_not_found)
    }

    return resp?.errorBody()?.string() ?: exception.message()
}
