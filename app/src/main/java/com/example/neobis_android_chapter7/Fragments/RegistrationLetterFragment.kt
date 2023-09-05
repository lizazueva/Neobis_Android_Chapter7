package com.example.neobis_android_chapter7.Fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.fragment.findNavController
import com.example.neobis_android_chapter7.R
import com.example.neobis_android_chapter7.databinding.AlertDialogBinding
import com.example.neobis_android_chapter7.databinding.AlertDialogExitBinding
import com.example.neobis_android_chapter7.databinding.FragmentRegistrationLetterBinding

class RegistrationLetterFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationLetterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationLetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageBack.setOnClickListener {
            findNavController().navigate(R.id.action_registrationLetterFragment_to_registrationFragment)
        }

        binding.textLetter.setOnClickListener {
            callDialogLetter()
        }

        colorText()
    }

    private fun callDialogLetter() {
        val dialogBinding = AlertDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        dialogBinding.buttonClose.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun colorText() {
        val text = getString(R.string.text_letter_no)
        val splitIndex = text.indexOf("лучше")

        val spannableString = SpannableString(text)

        spannableString.setSpan(
            ForegroundColorSpan(Color.GRAY),
            0,
            splitIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            ForegroundColorSpan(Color.BLACK),
            splitIndex,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textLetterNo.text = spannableString
    }
}