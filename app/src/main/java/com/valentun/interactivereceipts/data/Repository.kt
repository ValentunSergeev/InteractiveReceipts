package com.valentun.interactivereceipts.data

import com.valentun.interactivereceipts.data.network.dto.TokenInfo

interface Repository {
    fun saveAuthInfo(tokenInfo: TokenInfo)
    fun getTokenInfo(): TokenInfo
    fun isLoggedIn(): Boolean
    fun clearAuthInfo()
}