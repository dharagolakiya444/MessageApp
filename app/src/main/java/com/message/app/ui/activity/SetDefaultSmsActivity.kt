package com.message.app.ui.activity

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.provider.Telephony
import android.widget.Toast
import com.message.app.R
import com.message.app.databinding.ActivitySetDefaultSmsBinding
import com.message.app.ui.base.BaseActivity

class SetDefaultSmsActivity : BaseActivity() {

    private val REQUEST_CODE_DEFAULT_SMS = 11
    private lateinit var binding: ActivitySetDefaultSmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetDefaultSmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set title text color and style dynamically if needed
        binding.tvTitle.setTextColor(themeTextColor)
        binding.tvMain.setTextColor(themeTextColor)
        // You can set subtitle color dynamically as well if you want:
        // binding.tvSub.setTextColor(someSubtitleColor)

        // Dynamic button bg color (with rounded corners)
        val bgDrawable = binding.btnNext.background as? GradientDrawable
        bgDrawable?.setColor(themePrimaryColor)
        // Set button text color (optional, for theme contrast)
        binding.btnNext.setTextColor(buttonTextColor)

        binding.btnNext.setOnClickListener {
            if (Telephony.Sms.getDefaultSmsPackage(this) != packageName) {
                // Request user to make this app the default SMS app
                val intent = Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT)
                intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packageName)
                startActivityForResult(intent, REQUEST_CODE_DEFAULT_SMS)
            } else {
                goToHome()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_DEFAULT_SMS) {
            if (Telephony.Sms.getDefaultSmsPackage(this) == packageName) {
                goToHome()
            } else {
                Toast.makeText(this,
                    getString(R.string.please_set_as_default_sms_app_to_proceed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}