package com.valentun.interactiverecipes.data.actual

import com.valentun.interactiverecipes.data.Repository
import com.valentun.interactiverecipes.data.local.LocalDataManager
import com.valentun.interactiverecipes.data.network.ApiService
import com.valentun.interactiverecipes.data.pojo.Recipe
import com.valentun.interactiverecipes.data.pojo.RecipeOverview
import com.valentun.interactiverecipes.data.pojo.TokenInfo


class ActualRepository(
    val api: ApiService,
    val localManager: LocalDataManager
) : Repository {
    override fun saveAuthInfo(tokenInfo: TokenInfo) {
        localManager.saveTokenInfo(tokenInfo)
    }

    override fun getTokenInfo(): TokenInfo = localManager.getTokenInfo()

    override fun isLoggedIn(): Boolean = localManager.isAuthenticated()

    override fun clearAuthInfo() = localManager.clearUserData()

    override suspend fun getRecipes(): List<RecipeOverview> = api.getRecipesAsync().await()

    override suspend fun getRecipe(id: Long): Recipe = api.getRecipeAsync(id).await()
}