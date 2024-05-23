package com.saoke.joyreader.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.saoke.joyreader.api.Retrofit
import com.saoke.joyreader.databinding.FragmentLoginBinding
import com.saoke.joyreader.logic.model.LoginModel
import com.saoke.joyreader.logic.model.Model
import com.saoke.joyreader.logic.model.UserModel
import com.tencent.mmkv.MMKV
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(layoutInflater)

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
                        val mmkv = MMKV.defaultMMKV()
                        mmkv.encode("token", model?.data?.token)
                        mmkv.encode(
                            "avatar_url",
                            model?.data?.avatarUrl
                                ?: "http://west2-work4-pany0593.oss-cn-shenzhen.aliyuncs.com/avatar/default-avatar.png"
                        )
                        Toast.makeText(activity, model?.base?.message, Toast.LENGTH_SHORT)
                            .show()
                        getUser()
                    } else {
                        Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<Model<LoginModel>>, t: Throwable) {
                    Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.registerButton.setOnClickListener {
            (activity as AuthActivity).replaceFragment(RegisterFragment())
        }

        return binding.root
    }

    private fun getUser() {
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
                    Log.i("MyLog", "用户名：$username")
                    Log.i("MyLog", "头像：$avatarUrl")
                } else {
                    Log.i("MyLog", "getUser：${response.code()}")
                }
            }

            override fun onFailure(call: Call<Model<UserModel>>, t: Throwable) {
                Log.i("MyLog", "请求失败: ${t.message}")
            }
        })
    }
}