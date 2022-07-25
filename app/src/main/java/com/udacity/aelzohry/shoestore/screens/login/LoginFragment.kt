package com.udacity.aelzohry.shoestore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.udacity.aelzohry.shoestore.R
import com.udacity.aelzohry.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup click listeners
        binding.loginButton.setOnClickListener { onLogin() }
        binding.registerButton.setOnClickListener { onRegister() }
    }

    private fun onLogin() {
        onAuthenticationSucceed()
    }

    private fun onRegister() {
        onAuthenticationSucceed()
    }

    private fun onAuthenticationSucceed() {

    }

}