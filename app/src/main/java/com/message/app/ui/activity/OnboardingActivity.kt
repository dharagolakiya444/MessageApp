package com.message.app.ui.activity

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.message.app.R
import com.message.app.data.OnboardingModel
import com.message.app.ui.base.BaseActivity
import com.message.app.databinding.ActivityOnboardingBinding
import com.message.app.ui.adapters.OnboardingPagerAdapter

class OnboardingActivity : BaseActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardingPages: List<OnboardingModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Prepare your pages (add your drawable resources)
        onboardingPages = listOf(
            OnboardingModel(
                R.drawable.ic_intro_1, // Replace with your asset
                getString(R.string.stay_connected_anywhere),
                getString(R.string.chat_across_borders_with_lightning_fast_messages_and_no_limits)
            ),
            OnboardingModel(
                R.drawable.ic_intro_2, // Replace with your asset
                getString(R.string.all_in_one_messaging_hub),
                getString(R.string.text_voice_and_emojis_everything_you_need_to_express_yourself_in_one_place)
            )
        )

        binding.onboardingViewPager.adapter = OnboardingPagerAdapter(this, onboardingPages)
        setThemeColors()

        binding.btnNext.setOnClickListener {
            if (binding.onboardingViewPager.currentItem < onboardingPages.lastIndex) {
                binding.onboardingViewPager.currentItem += 1
            } else {
                startActivity(Intent(this, SetDefaultSmsActivity::class.java))
                finish()
            }
        }

        binding.onboardingViewPager.registerOnPageChangeCallback(object: androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setThemeColors()
            }
        })
    }

    private fun setThemeColors() {

        val bgDrawable = binding.btnNext.background as? GradientDrawable
        bgDrawable?.setColor(themePrimaryColor)

        binding.btnNext.setTextColor(buttonTextColor)

        binding.onboardingRoot.setBackgroundColor(themeBackgroundColor)
    }
}