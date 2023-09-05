package com.example.neobis_android_chapter7.Fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.neobis_android_chapter7.MainActivity
import com.example.neobis_android_chapter7.R
import com.example.neobis_android_chapter7.databinding.AlertDialogBinding
import com.example.neobis_android_chapter7.databinding.FragmentAuthorizationBinding
import com.example.neobis_android_chapter7.databinding.FragmentRegistrationBinding
import com.example.neobis_android_chapter7.viewModel.MyViewModel
import android.content.res.ColorStateList



class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    lateinit var viewModelRegistrationFragment: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelRegistrationFragment = (activity as MainActivity).myViewModel

        binding.imageBack.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }

        checkInput()


    }

    private fun checkInput() {
        binding.editTextMail.addTextChangedListener(inputText)
        binding.editTextLogin.addTextChangedListener(inputText)
        binding.editTextPassword.addTextChangedListener(inputText)
        binding.repeatPassword.addTextChangedListener(inputText)

        binding.buttonFurther.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_registrationLetterFragment)

        }
    }

    private val inputText = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            var isEmailValid = false
            var isLoginValid = false

            val emailInput = binding.editTextMail.text.toString().trim()
            val loginInput = binding.editTextLogin.text.toString().trim()
            val passwordInput = binding.editTextPassword.text.toString().trim()
            val repeatPasswordInput = binding.repeatPassword.text.toString().trim()

            val buttonFurther = binding.buttonFurther

            validateEmail(emailInput)
            validateLogin(loginInput)
            validatePassword(passwordInput)

            if (binding.textInputLayoutEditTextMail.helperText == null
                && binding.textInputLayoutEditTextMail.helperText == null) {
                isEmailValid = true
                isLoginValid = true
            }


            buttonFurther.isEnabled = isEmailValid && isLoginValid
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }

    private fun validateEmail(email: String) {
        val isEmailMatches = email.matches(Regex("[A-Za-z@.]*"))
        val isEmailEmpty = email.isEmpty()
        val isEmailContains = email.contains('@') && email.contains('.')

        if (!isEmailMatches) {
            binding.textInputLayoutEditTextMail.helperText = "Присутствуют недопустимые символы"
            binding.textInputLayoutEditTextMail.editText?.setTextColor(Color.RED)
            binding.textInputLayoutEditTextMail.setErrorTextColor(ColorStateList.valueOf(Color.RED))


        } else if (isEmailEmpty) {
            binding.textInputLayoutEditTextMail.helperText = "Заполните это поле"
            binding.textInputLayoutEditTextMail.editText?.setTextColor(Color.RED)

        } else if (!isEmailContains) {
            binding.textInputLayoutEditTextMail.helperText = "Нет специальных символов @, ."
            binding.textInputLayoutEditTextMail.editText?.setTextColor(Color.RED)

        } else {
            binding.textInputLayoutEditTextMail.helperText = null
            binding.textInputLayoutEditTextMail.editText?.setTextColor(Color.BLACK)

        }
    }

    private fun validateLogin(login: String) {

        val isLoginEmpty = login.isEmpty()
        val isLoginMatches = login.matches(Regex("[A-Za-z]*"))

        if (!isLoginMatches) {
            binding.textInputLayoutEditTextLogin.helperText = "Присутствуют недопустимые символы"
            binding.textInputLayoutEditTextLogin.editText?.setTextColor(Color.RED)
            binding.textInputLayoutEditTextLogin.setErrorTextColor(ColorStateList.valueOf(Color.RED))
        } else if (isLoginEmpty) {
            binding.textInputLayoutEditTextLogin.helperText = "Заполните это поле"
            binding.textInputLayoutEditTextLogin.editText?.setTextColor(Color.RED)
            binding.textInputLayoutEditTextLogin.setErrorTextColor(ColorStateList.valueOf(Color.RED))
        } else {
            binding.textInputLayoutEditTextLogin.helperText = null
            binding.textInputLayoutEditTextLogin.editText?.setTextColor(Color.BLACK)
            binding.textInputLayoutEditTextLogin.setErrorTextColor(ColorStateList.valueOf(Color.BLACK))

        }
    }

    private fun validatePassword(password: String) {

        val isPasswordEmpty = password.isEmpty()
        val isPasswordMatches = password.matches(Regex("[A-Za-z]+"))
        val containsUppercase = password.matches(Regex(".*[A-Z].*"))
        val containsLowercase = password.matches(Regex(".*[a-z].*"))
        val containsSpecialCharacter = password.matches(Regex(".*[!\"#\$%&'()*+,-./:;<=>?@\\[\\\\\\]^_`{|}~].*"))
        val isPasswordLength = password.length !in 8..15
        val isPasswordOneNumber = password.any { it.isDigit() }

        val helperText1 = binding.helperText1
        val helperText2 = binding.helperText2
        val helperText3 = binding.helperText3
        val helperText4 = binding.helperText4

        val imageHelperText1 = binding.imageHelperText1
        val imageHelperText2 = binding.imageHelperText2
        val imageHelperText3 = binding.imageHelperText3
        val imageHelperText4 = binding.imageHelperText4

        val validLength = if (isPasswordLength && !isPasswordEmpty) {
            helperText1.setTextColor(Color.RED)
            imageHelperText1.setImageResource(R.drawable.nocheck)
            imageHelperText1.visibility = View.VISIBLE
            } else if (isPasswordEmpty){
                imageHelperText1.visibility = View.INVISIBLE
                helperText1.setTextColor(Color.GRAY)
            }else {
                helperText1.setTextColor(Color.GREEN)
                imageHelperText1.setImageResource(R.drawable.check)
                imageHelperText1.visibility = View.VISIBLE
                true
            }

        val validMatches =  if ((!containsUppercase || !containsLowercase) && !isPasswordEmpty) {
            helperText2.setTextColor(Color.RED)
            imageHelperText2.setImageResource(R.drawable.nocheck)
            imageHelperText2.visibility = View.VISIBLE
        } else if (isPasswordEmpty) {
            helperText2.setTextColor(Color.GRAY)
            imageHelperText2.visibility = View.INVISIBLE
        } else {
            helperText2.setTextColor(Color.GREEN)
            imageHelperText2.setImageResource(R.drawable.check)
            imageHelperText2.visibility = View.VISIBLE
            true
        }

        val validOneNumber = if (!isPasswordOneNumber) {
            helperText3.setTextColor(Color.RED)
            imageHelperText3.setImageResource(R.drawable.nocheck)
            imageHelperText3.visibility = View.VISIBLE
        } else {
            helperText3.setTextColor(Color.GREEN)
            imageHelperText3.setImageResource(R.drawable.check)
            imageHelperText3.visibility = View.VISIBLE
            true
        }

        val validSpecialCharacter = if (!containsSpecialCharacter && !isPasswordEmpty) {
            helperText4.setTextColor(Color.RED)
            imageHelperText4.setImageResource(R.drawable.nocheck)
            imageHelperText4.visibility = View.VISIBLE
        } else {
            helperText4.setTextColor(Color.GREEN)
            imageHelperText4.setImageResource(R.drawable.check)
            imageHelperText4.visibility = View.VISIBLE
            true
        }

        if (isPasswordEmpty) {
            helperText1.setTextColor(Color.GRAY)
            imageHelperText1.visibility = View.INVISIBLE
            helperText2.setTextColor(Color.GRAY)
            imageHelperText2.visibility = View.INVISIBLE
            helperText3.setTextColor(Color.GRAY)
            imageHelperText3.visibility = View.INVISIBLE
            helperText4.setTextColor(Color.GRAY)
            imageHelperText4.visibility = View.INVISIBLE

        }

        }


//        fun valide():Boolean{
//            if((validLength && validMatches && validOneNumber)== true){
//                true
//            }
//        }
//        return valide
        }