package com.saoke.joyreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saoke.joyreader.databinding.ActivitySettingsBinding
import com.saoke.joyreader.ui.auth.AuthActivity
import com.tencent.mmkv.MMKV

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.username.text = MMKV.defaultMMKV().decodeString("username", "无名氏")

        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }
}