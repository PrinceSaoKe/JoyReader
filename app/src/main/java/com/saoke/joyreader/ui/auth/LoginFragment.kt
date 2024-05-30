package com.saoke.joyreader.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.saoke.joyreader.api.Retrofit
import com.saoke.joyreader.databinding.FragmentLoginBinding
import com.saoke.joyreader.logic.model.LoginModel
import com.saoke.joyreader.logic.model.Model
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
            if (binding.usernameEditText.text.isEmpty() || binding.passwordEditText.text.isEmpty()) {
                Toast.makeText(activity, "请输入用户名和密码", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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
                        if (response.body()!!.base.code == 0) {
                            Retrofit.getUser()
                            activity!!.finish()
                        }
                    } else {
                        Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()
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
}