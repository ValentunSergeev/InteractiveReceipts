package com.valentun.interactiverecipes.di

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.valentun.interactiverecipes.data.network.ApiService
import com.valentun.interactiverecipes.BuildConfig
import com.valentun.interactiverecipes.data.network.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

private const val MAX_TIMEOUT = 15L

private const val BASE_API_URL = BuildConfig.BASE_URL

val networkModule = module {
    single { AuthInterceptor(get()) }

    single { createClient(get()) }

    single { createRetrofit(get(), BASE_API_URL) }

    single { get<Retrofit>().create(ApiService::class.java) }
}

private fun createClient(authInterceptor: AuthInterceptor) = OkHttpClient.Builder()
    .addInterceptor(authInterceptor)
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .connectTimeout(MAX_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(MAX_TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(MAX_TIMEOUT, TimeUnit.SECONDS)
    .build()

private fun createRetrofit(okHttp: OkHttpClient, baseUrl: String) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(JacksonConverterFactory.create(createMapper()))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(okHttp)
    .build()

fun createMapper(): ObjectMapper {
    val mapper = ObjectMapper()

    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    return mapper
}
