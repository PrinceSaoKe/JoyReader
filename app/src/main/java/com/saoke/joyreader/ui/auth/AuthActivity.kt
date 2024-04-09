package com.saoke.joyreader.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.saoke.joyreader.api.Retrofit
import com.saoke.joyreader.databinding.ActivityAuthBinding
import com.saoke.joyreader.logic.model.LoginModel
import com.saoke.joyreader.logic.model.Model
import com.tencent.mmkv.MMKV
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            Retrofit.api.login(
                binding.usernameEditText.text.toString(),
                binding.passwordEditText.text.toString()
            ).enqueue(object : Callback<Model<LoginModel>> {
                override fun onResponse(
                    call: Call<Model<LoginModel>>,
                    response: Response<Model<LoginModel>>
                ) {
                    if (response.isSuccessful) {
                        val model = response.body()
                        MMKV.defaultMMKV().encode("token", model?.data?.token)
                        Toast.makeText(this@AuthActivity, model?.base?.message, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this@AuthActivity, response.message(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<Model<LoginModel>>, t: Throwable) {
                    Toast.makeText(this@AuthActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.registerButton.setOnClickListener{
            // TODO 跳转注册页面
        }
    }
}