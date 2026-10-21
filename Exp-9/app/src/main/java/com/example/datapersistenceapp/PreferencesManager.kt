package com.example.datapersistenceapp

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_NAME = "user_name"
        private const val KEY_USN = "user_usn"
        private const val KEY_REMEMBER_ME = "remember_me"
        private const val KEY_LAST_LOGIN = "last_login"
    }

    fun saveUserSession(name: String, usn: String, rememberMe: Boolean) {
        val editor = prefs.edit()
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_USN, usn)
        editor.putBoolean(KEY_REMEMBER_ME, rememberMe)
        editor.putString(KEY_LAST_LOGIN, java.text.DateFormat.getDateTimeInstance().format(java.util.Date()))
        editor.apply()
    }

    fun getUserName(): String = prefs.getString(KEY_NAME, "Siddhi Assudani") ?: "Siddhi Assudani"

    fun getUserUsn(): String = prefs.getString(KEY_USN, "25MCAR0199") ?: "25MCAR0199"

    fun isRememberMe(): Boolean = prefs.getBoolean(KEY_REMEMBER_ME, false)

    fun getLastLogin(): String = prefs.getString(KEY_LAST_LOGIN, "First Login") ?: "First Login"

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
