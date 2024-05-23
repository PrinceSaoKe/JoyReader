package com.saoke.joyreader.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.saoke.joyreader.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegisterBinding.inflate(layoutInflater)

        return binding.root
    }
}