package com.saoke.joyreader.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.saoke.joyreader.R
import com.saoke.joyreader.api.Retrofit
import com.saoke.joyreader.databinding.FragmentRegisterBinding
import com.saoke.joyreader.logic.model.BaseModel
import com.saoke.joyreader.logic.model.LoginModel
import com.saoke.joyreader.logic.model.Model
import com.tencent.mmkv.MMKV
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegisterBinding.inflate(layoutInflater)

        binding.registerButton.setOnClickListener {
            if (binding.usernameEditText.text.isEmpty() || binding.passwordEditText.text.isEmpty() || binding.confirmPasswordEditText.text.isEmpty()) {
                Toast.makeText(activity, "请输入用户名和密码", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.passwordEditText.text.toString() != binding.confirmPasswordEditText.text.toString()) {
                Toast.makeText(activity, "两次密码不一致", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Retrofit.api.register(
                binding.usernameEditText.text.toString(),
                binding.passwordEditText.text.toString()
            ).enqueue(object : Callback<Model<BaseModel>> {
                override fun onResponse(
                    call: Call<Model<BaseModel>>,
                    response: Response<Model<BaseModel>>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(activity, response.body()!!.base.message, Toast.LENGTH_SHORT)
                            .show()
                        if (response.body()!!.base.code == 0) (activity as AuthActivity).replaceFragment(
                            LoginFragment()
                        )
                    } else {
                        Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Model<BaseModel>>, t: Throwable) {
                    Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

        return binding.root
    }
}