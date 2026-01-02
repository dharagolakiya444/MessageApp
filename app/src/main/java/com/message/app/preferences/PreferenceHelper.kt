package com.message.app.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper private constructor(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "your_app_prefs"

        @Volatile
        private var INSTANCE: PreferenceHelper? = null

        fun getInstance(context: Context): PreferenceHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PreferenceHelper(context).also { INSTANCE = it }
            }

        // Preference keys
        const val KEY_THEME_PRIMARY = "theme_primary_color"
        const val KEY_THEME_BACKGROUND = "theme_background_color"
        const val KEY_THEME_TEXT = "theme_text_color"
        const val KEY_THEME_TEXT_BUTTON = "theme_text_color_button"
    }

    // Theme color functions
    fun saveThemeColors(primary: Int, background: Int, text: Int,textButton:Int) {
        prefs.edit()
            .putInt(KEY_THEME_PRIMARY, primary)
            .putInt(KEY_THEME_BACKGROUND, background)
            .putInt(KEY_THEME_TEXT, text)
            .putInt(KEY_THEME_TEXT_BUTTON, textButton)
            .apply()
    }

    fun getThemePrimaryColor(): Int {
        return prefs.getInt(KEY_THEME_PRIMARY, 0)
    }

    fun getThemeBackgroundColor(): Int {
        return prefs.getInt(KEY_THEME_BACKGROUND, 0)
    }

    fun getThemeTextColor(): Int {
        return prefs.getInt(KEY_THEME_TEXT, 0)
    }
    fun getThemeButtonTextColor(): Int {
        return prefs.getInt(KEY_THEME_TEXT_BUTTON, 0)
    }
    // ... other functions
}