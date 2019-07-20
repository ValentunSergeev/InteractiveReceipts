package com.valentun.interactiverecipes.data.network

import com.valentun.interactiverecipes.data.local.LocalDataManager
import okhttp3.Interceptor
import okhttp3.Response

private const val AUTH_HEADER_NAME = "Authorization"

private const val TOKEN_PREFIX = "Token"

class AuthInterceptor(private val manager: LocalDataManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (manager.isAuthenticated()) {
            val tokenInfo = manager.getTokenInfo()

            val fullToken = "$TOKEN_PREFIX ${tokenInfo.token}"

            builder.addHeader(AUTH_HEADER_NAME, fullToken)
        }

        return chain.proceed(builder.build())
    }
}