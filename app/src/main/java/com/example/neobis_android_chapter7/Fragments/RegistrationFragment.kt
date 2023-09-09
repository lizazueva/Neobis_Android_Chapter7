package com.example.neobis_android_chapter7.Fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.neobis_android_chapter7.MainActivity
import com.example.neobis_android_chapter7.R
import com.example.neobis_android_chapter7.databinding.FragmentRegistrationBinding
import com.example.neobis_android_chapter7.viewModel.MyViewModel
import android.content.res.ColorStateList
import android.widget.Toast
import com.example.neobis_android_chapter7.utils.Resource


class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    lateinit var viewModelRegistrationFragment: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        clickButton()
    }

    private fun clickButton(){
        binding.buttonFurther.setOnClickListener {

            var mail = binding.editTextMail.text.toString().trim()
            var username = binding.editTextLogin.text.toString().trim()
            var password1 = binding.editTextPassword.text.toString().trim()
            var password2 = binding.repeatPassword.text.toString().trim()
            viewModelRegistrationFragment.newUser(mail, username, password1, password2)

            observe()

        }
    }

    private fun observe() {
        viewModelRegistrationFragment.userSaved.observe(viewLifecycleOwner, {userSaved->
            when(userSaved) {
                is Resource.Success -> {
                    var mail = binding.editTextMail.text.toString().trim()
                    val action = RegistrationFragmentDirections.actionRegistrationFragmentToRegistrationLetterFragment(mail)
                    findNavController().navigate(action)
                    findNavController().navigate(R.id.action_registrationFragment_to_registrationLetterFragment)
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Пользовательне не зарегестрирован", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    // загрузка
                }
            }
        })
    }

    private val inputText = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            var isEmailValid = false
            var isLoginValid = false
            var isPasswordValid = false
            var isPasswordRepeatValid = false


            val emailInput = binding.editTextMail.text.toString().trim()
            val loginInput = binding.editTextLogin.text.toString().trim()
            val passwordInput = binding.editTextPassword.text.toString().trim()
            val repeatPasswordInput = binding.repeatPassword.text.toString().trim()

            val buttonFurther = binding.buttonFurther

            validateEmail(emailInput)
            validateLogin(loginInput)
            val resultValidPassword = validatePassword(passwordInput)
            validatePasswordRepeat(repeatPasswordInput)



            if (binding.textInputLayoutEditTextMail.helperText == null
                && binding.textInputLayoutEditTextLogin.helperText == null && resultValidPassword
                && binding.textInputLayoutPasswordRepeat.helperText ==null
            ) {
                isEmailValid = true
                isLoginValid = true
                isPasswordValid = true
                isPasswordRepeatValid = true

            }


            buttonFurther.isEnabled = isEmailValid && isLoginValid && isPasswordValid && isPasswordRepeatValid
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }

    private fun validatePasswordRepeat(repeatPasswordInput: String) {
        val isPasswordEmpty = repeatPasswordInput.isEmpty()
        val passwordInput = binding.editTextPassword.text.toString().trim()

        if (repeatPasswordInput != passwordInput) {
            binding.textInputLayoutPasswordRepeat.helperText = "Пароли не совпадают"
            binding.textInputLayoutPasswordRepeat.editText?.setTextColor(Color.RED)
            binding.textInputLayoutPasswordRepeat.setErrorTextColor(ColorStateList.valueOf(Color.RED))
        } else if (isPasswordEmpty) {
            binding.textInputLayoutPasswordRepeat.helperText = "Заполните это поле"
            binding.textInputLayoutPasswordRepeat.editText?.setTextColor(Color.RED)
            binding.textInputLayoutPasswordRepeat.setErrorTextColor(ColorStateList.valueOf(Color.RED))
        } else {
            binding.textInputLayoutPasswordRepeat.helperText = null
            binding.textInputLayoutPasswordRepeat.editText?.setTextColor(Color.BLACK)
            binding.textInputLayoutPasswordRepeat.setErrorTextColor(ColorStateList.valueOf(Color.BLACK))

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

    private fun validatePassword(password: String): Boolean {

        val isPasswordEmpty = password.isEmpty()
        val isPasswordMatches = password.matches(Regex("[A-Za-z]+"))
        val containsUppercase = password.matches(Regex(".*[A-Z].*"))
        val containsLowercase = password.matches(Regex(".*[a-z].*"))
        val containsSpecialCharacter =
            password.matches(Regex(".*[!\"#\$%&'()*+,-./:;<=>?@\\[\\\\\\]^_`{|}~].*"))
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
            binding.textInputLayoutPassword.editText?.setTextColor(Color.RED)
            imageHelperText1.setImageResource(R.drawable.nocheck)
            imageHelperText1.visibility = View.VISIBLE
            false
        }  else {
            helperText1.setTextColor(Color.parseColor("#1BA228"))
            imageHelperText1.setImageResource(R.drawable.check)
            imageHelperText1.visibility = View.VISIBLE
            true
        }

        val validMatches = if ((!containsUppercase || !containsLowercase) && !isPasswordEmpty) {
            helperText2.setTextColor(Color.RED)
            binding.textInputLayoutPassword.editText?.setTextColor(Color.RED)
            imageHelperText2.setImageResource(R.drawable.nocheck)
            imageHelperText2.visibility = View.VISIBLE
            false
        }  else {
            helperText2.setTextColor(Color.parseColor("#1BA228"))
            imageHelperText2.setImageResource(R.drawable.check)
            imageHelperText2.visibility = View.VISIBLE
            true
        }

        val validOneNumber = if (!isPasswordOneNumber) {
            helperText3.setTextColor(Color.RED)
            binding.textInputLayoutPassword.editText?.setTextColor(Color.RED)
            imageHelperText3.setImageResource(R.drawable.nocheck)
            imageHelperText3.visibility = View.VISIBLE
            false
        } else {
            helperText3.setTextColor(Color.parseColor("#1BA228"))
            imageHelperText3.setImageResource(R.drawable.check)
            imageHelperText3.visibility = View.VISIBLE
            true
        }

        val validSpecialCharacter = if (!containsSpecialCharacter && !isPasswordEmpty) {
            helperText4.setTextColor(Color.RED)
            binding.textInputLayoutPassword.editText?.setTextColor(Color.RED)
            imageHelperText4.setImageResource(R.drawable.nocheck)
            imageHelperText4.visibility = View.VISIBLE
            false
        } else {
            helperText4.setTextColor(Color.parseColor("#1BA228"))
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
        val isAllValid = validSpecialCharacter && validOneNumber && validMatches && validLength

        if (isAllValid) {
            binding.textInputLayoutPassword.editText?.setTextColor(Color.BLACK)
        }

        return isAllValid

    }
}