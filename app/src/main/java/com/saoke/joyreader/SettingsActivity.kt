package com.saoke.joyreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saoke.joyreader.R
import com.saoke.joyreader.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val binding = ActivitySettingsBinding.inflate(layoutInflater)

        binding.avatar.setImageResource(R.drawable.avatar)
        binding.logoutButton.setOnClickListener {
            // TODO 跳转登录页面
        }
    }
}