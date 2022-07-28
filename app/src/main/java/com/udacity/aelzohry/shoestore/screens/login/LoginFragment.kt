package com.udacity.aelzohry.shoestore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.aelzohry.shoestore.R
import com.udacity.aelzohry.shoestore.databinding.FragmentLoginBinding
import com.udacity.aelzohry.shoestore.extensions.hideKeyboard

class LoginFragment : Fragment() {

    /* I didn't create & use ViewModel and LiveData because there's nothing happens here
        (no validating, no real authentication),
        just a click listener to go to the next screen (onboarding)
        The same thing in other screens
     */

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
        hideKeyboard()
        onAuthenticationSucceed()
    }

    private fun onRegister() {
        hideKeyboard()
        onAuthenticationSucceed()
    }

    private fun onAuthenticationSucceed() {
        // Go To Welcome Screen
        val destination = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        findNavController().navigate(destination)
    }

}