package com.example.neobis_android_chapter7.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neobis_android_chapter7.R
import com.example.neobis_android_chapter7.databinding.FragmentReturnBinding


class ReturnFragment : Fragment() {

    private lateinit var binding: FragmentReturnBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentReturnBinding.inflate(inflater, container, false)
        return binding.root
    }

}