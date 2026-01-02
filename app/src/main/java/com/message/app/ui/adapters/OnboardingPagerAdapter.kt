package com.message.app.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.message.app.data.OnboardingModel
import com.message.app.ui.fragments.OnboardingFragment

class OnboardingPagerAdapter(
    fa: FragmentActivity,
    private val pages: List<OnboardingModel>
): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment {
        return OnboardingFragment.newInstance(pages[position])
    }
}