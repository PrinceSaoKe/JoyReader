package com.saoke.joyreader

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.saoke.joyreader.api.Retrofit
import com.saoke.joyreader.databinding.ActivityMainBinding
import com.saoke.joyreader.logic.model.Model
import com.saoke.joyreader.logic.model.UserModel
import com.tencent.mmkv.MMKV
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mmkvPath = MMKV.initialize(this)
        Log.i("JoyReader", "MMKV存储路径: $mmkvPath")

        initData()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun initData() {
        Retrofit.api.getUser().enqueue(object : Callback<Model<UserModel>> {
            override fun onResponse(
                call: Call<Model<UserModel>>,
                response: Response<Model<UserModel>>
            ) {
                if (response.isSuccessful) {
                    val mmkv = MMKV.defaultMMKV()
                    val userId = response.body()?.data?.userId
                    val username = response.body()?.data?.username
                    val avatarUrl = response.body()?.data?.avatarUrl
                    if (userId != null) mmkv.encode("user_id", userId)
                    if (username != null) mmkv.encode("username", username)
                    if (avatarUrl != null) mmkv.encode("avatar_url", avatarUrl)
                    Log.i("JoyReader", "用户名：$username")
                    Log.i("JoyReader", "头像：$avatarUrl")
                } else {
                    Log.i("JoyReader", "getUser：${response.code()}")
                }
            }

            override fun onFailure(call: Call<Model<UserModel>>, t: Throwable) {
                Log.i("JoyReader", "请求失败: ${t.message}")
            }
        })
    }
}