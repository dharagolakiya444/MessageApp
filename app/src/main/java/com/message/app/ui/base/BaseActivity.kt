package com.message.app.ui.base

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.message.app.preferences.PreferenceHelper

open class BaseActivity : AppCompatActivity() {

    var themePrimaryColor = 0
    var themeBackgroundColor = 0
    var themeTextColor = 0
    var buttonTextColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDefaultThemeColorForText()

        setupThemeColors()
    }

    private fun setupThemeColors() {
        themePrimaryColor = PreferenceHelper.getInstance(this).getThemePrimaryColor()
        themeBackgroundColor = PreferenceHelper.getInstance(this).getThemeBackgroundColor()
        themeTextColor = PreferenceHelper.getInstance(this).getThemeTextColor()
        buttonTextColor = PreferenceHelper.getInstance(this).getThemeButtonTextColor()
    }

    private fun setDefaultThemeColorForText() {
        PreferenceHelper.getInstance(this).saveThemeColors(
            Color.parseColor("#1C4EF4"),
            Color.parseColor("#FFFFFF"),
            Color.parseColor("#1A1A1A"),
            Color.parseColor("#FFFFFF")
//                   ContextCompat.getColor(context, R.color.colorPrimary)
        )
    }

}