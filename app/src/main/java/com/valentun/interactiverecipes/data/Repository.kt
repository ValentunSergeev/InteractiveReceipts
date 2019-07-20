package com.valentun.interactiverecipes.data

import com.valentun.interactiverecipes.data.pojo.TokenInfo

interface Repository {
    fun saveAuthInfo(tokenInfo: TokenInfo)
    fun getTokenInfo(): TokenInfo
    fun isLoggedIn(): Boolean
    fun clearAuthInfo()
}