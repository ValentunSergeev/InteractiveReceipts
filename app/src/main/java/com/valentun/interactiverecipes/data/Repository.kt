package com.valentun.interactiverecipes.data

import com.valentun.interactiverecipes.data.pojo.Recipe
import com.valentun.interactiverecipes.data.pojo.RecipeOverview
import com.valentun.interactiverecipes.data.pojo.TokenInfo

interface Repository {
    fun saveAuthInfo(tokenInfo: TokenInfo)
    fun getTokenInfo(): TokenInfo
    fun isLoggedIn(): Boolean
    fun clearAuthInfo()

    suspend fun getRecipes(): List<RecipeOverview>
    suspend fun getRecipe(id: Long) : Recipe
}