package com.valentun.interactiverecipes.data.network

import com.valentun.interactiverecipes.data.pojo.Recipe
import com.valentun.interactiverecipes.data.pojo.RecipeOverview
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("recipes")
    fun getRecipesAsync() : Deferred<List<RecipeOverview>>

    @GET("recipes/{id}")
    fun getRecipeAsync(@Path("id") id: Long) : Deferred<Recipe>
}
