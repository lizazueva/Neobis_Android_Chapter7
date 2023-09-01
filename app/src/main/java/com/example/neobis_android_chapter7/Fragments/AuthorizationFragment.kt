package com.example.neobis_android_chapter7.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neobis_android_chapter7.MainActivity
import com.example.neobis_android_chapter7.R
import com.example.neobis_android_chapter7.databinding.FragmentAuthorizationBinding
import com.example.neobis_android_chapter7.viewModel.MyViewModel

class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding
    lateinit var viewModelAuthorizationFragment: MyViewModel

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



    }

}