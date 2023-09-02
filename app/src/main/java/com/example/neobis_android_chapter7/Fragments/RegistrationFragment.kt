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

    private fun callDialog() {
        val dialogBinding = AlertDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val email = binding.editTextMail.text.toString().trim()
        val textAlert = getString(R.string.text_alert, email)

        val spannableString = SpannableString(textAlert)
        val emailStartIndex = textAlert.indexOf(email)
        val emailEndIndex = emailStartIndex + email.length

        spannableString.setSpan(
            ForegroundColorSpan(Color.BLUE),
            emailStartIndex,
            emailEndIndex,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        dialogBinding.textAlert.text = spannableString

        dialogBinding.buttonClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun checkInput() {
        binding.editTextMail.addTextChangedListener (inputText)
        binding.buttonFurther.setOnClickListener {
            callDialog()
        }
    }

    private val inputText = object:TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val emailInput = binding.editTextMail.text.toString().trim()
            val buttonFurther = binding.buttonFurther

            buttonFurther.isEnabled = emailInput.contains('@')&& emailInput.contains('.')
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }

}