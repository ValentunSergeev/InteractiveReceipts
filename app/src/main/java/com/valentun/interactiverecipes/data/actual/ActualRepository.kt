package com.valentun.interactiverecipes.data.actual

import com.valentun.interactiverecipes.data.Repository
import com.valentun.interactiverecipes.data.local.LocalDataManager
import com.valentun.interactiverecipes.data.network.ApiService
import com.valentun.interactiverecipes.data.pojo.TokenInfo


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