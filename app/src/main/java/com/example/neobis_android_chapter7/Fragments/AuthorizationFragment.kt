package com.example.neobis_android_chapter7.Fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.neobis_android_chapter7.MainActivity
import com.example.neobis_android_chapter7.R
import com.example.neobis_android_chapter7.databinding.FragmentAuthorizationBinding
import com.example.neobis_android_chapter7.utils.Resource
import com.example.neobis_android_chapter7.utils.SharePref
import com.example.neobis_android_chapter7.viewModel.MyViewModel
import com.google.android.material.snackbar.Snackbar


class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding
    lateinit var viewModelAuthorizationFragment: MyViewModel
    private lateinit var sharePref: SharePref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelAuthorizationFragment = (activity as MainActivity).myViewModel

        binding.textNoAccount.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }

        checkInput()

        binding.buttonFurther.setOnClickListener {
            val username = binding.editTextLogin.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            viewModelAuthorizationFragment.login(username, password)
            observe()
        }


    }

    private fun observe() {
        viewModelAuthorizationFragment.token.observe(viewLifecycleOwner, { token ->
            when (token) {
                is Resource.Success -> {
                    hideProgressBar()
                    sharPref()
                    sharePref.setFirstTimeUser(false)
                }
                is Resource.Error -> {
                    hideProgressBar()
                    snackBar()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
}

    private fun sharPref() {
        sharePref = SharePref(requireContext())

        // Проверка, является ли пользователь новым или нет
        if (sharePref.isFirstTimeUser()) {
            findNavController().navigate(R.id.action_authorizationFragment_to_welcomeFragment)
        } else {
            findNavController().navigate(R.id.action_authorizationFragment_to_returnFragment)
        }
    }


    private fun checkInput() {
        val loginInput = binding.editTextLogin.text.toString().trim()
        val passwordInput = binding.editTextPassword.text.toString().trim()
        binding.editTextLogin.setText(loginInput)
        binding.editTextPassword.setText(passwordInput)
    }

    private fun snackBar(){
        val snackbar = Snackbar.make(binding.root, "Неверный логин или пароль", Snackbar.LENGTH_SHORT)
        val params = snackbar.view.layoutParams as CoordinatorLayout.LayoutParams
        params.gravity = Gravity.TOP
        snackbar.view.layoutParams = params
        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        snackbar.view.background = ContextCompat.getDrawable(requireContext(), R.drawable.snackbar)
        snackbar.show()
    }

    private  fun hideProgressBar(){
        binding.progressBar.visibility = View.INVISIBLE
    }

    private  fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }
}