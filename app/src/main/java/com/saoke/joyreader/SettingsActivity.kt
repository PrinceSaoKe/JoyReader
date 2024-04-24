package com.saoke.joyreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.saoke.joyreader.databinding.ActivitySettingsBinding
import com.saoke.joyreader.ui.auth.AuthActivity
import com.tencent.mmkv.MMKV

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mmkv = MMKV.defaultMMKV()
        val avatarUrl = mmkv.decodeString(
            "avatar_url",
            "http://west2-work4-pany0593.oss-cn-shenzhen.aliyuncs.com/avatar/default-avatar.png"
        )!!
        Glide.with(this).load(avatarUrl).into(binding.avatar)

        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }
}