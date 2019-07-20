package com.valentun.interactivereceipts.data.actual

import com.valentun.interactivereceipts.data.Repository
import com.valentun.interactivereceipts.data.local.LocalDataManager
import com.valentun.interactivereceipts.data.network.ApiService
import com.valentun.interactivereceipts.data.network.dto.TokenInfo


class ActualRepository(
    val api: ApiService,
    val localManager: LocalDataManager
) : Repository {
    override fun saveAuthInfo(tokenInfo: TokenInfo) {
        TODO("not implemented")
    }

    override fun getTokenInfo(): TokenInfo {
        TODO("not implemented")
    }

    override fun isLoggedIn(): Boolean {
        TODO("not implemented")
    }

    override fun clearAuthInfo() {
        TODO("not implemented")
    }

}