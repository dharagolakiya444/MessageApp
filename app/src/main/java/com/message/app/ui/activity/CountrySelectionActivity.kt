package com.message.app.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.message.app.R
import com.message.app.data.CountryData
import com.message.app.databinding.ActivityCountrySelectionBinding
import com.message.app.ui.adapters.CountryAdapter
import com.message.app.ui.base.BaseActivity

class CountrySelectionActivity : BaseActivity() {

    private lateinit var binding: ActivityCountrySelectionBinding
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountrySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupRecyclerView()
    }

    private fun setupUI() {
        binding.root.setBackgroundColor(themeBackgroundColor)

        binding.incToolbar.tvTitle.apply {
            text = getString(R.string.select_country)
            setTextColor(themeTextColor)
        }

        binding.incToolbar.ivNext.setColorFilter(themePrimaryColor)

        binding.incToolbar.ivNext.setOnClickListener {
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = CountryAdapter(themePrimaryColor, themeTextColor)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        adapter.submitList(CountryData.getCountries())
    }
}
