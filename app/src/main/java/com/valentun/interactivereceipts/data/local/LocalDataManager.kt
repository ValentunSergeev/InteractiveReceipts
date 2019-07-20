package com.valentun.interactivereceipts.data.local

import android.content.Context
import android.preference.PreferenceManager
import com.fasterxml.jackson.databind.ObjectMapper
import com.valentun.interactivereceipts.data.network.dto.TokenInfo

private const val KEY_AUTH_TOKEN = "KEY_AUTH_TOKEN"

class LocalDataManager(context: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val mapper = ObjectMapper()


    fun getTokenInfo(): TokenInfo {
        val raw = preferences.getString(KEY_AUTH_TOKEN, null)
                ?: throw IllegalArgumentException("No $KEY_AUTH_TOKEN is stored")

        return mapper.readValue(raw, TokenInfo::class.java)
    }

    fun isAuthenticated() = preferences.contains(KEY_AUTH_TOKEN)

    fun clearUserData() {
        preferences.edit()
                .remove(KEY_AUTH_TOKEN)
                .apply()
    }

    fun saveTokenInfo(tokenInfo: TokenInfo) {
        val raw = mapper.writeValueAsString(tokenInfo)

        preferences.edit()
                .putString(KEY_AUTH_TOKEN, raw)
                .apply()
    }
}