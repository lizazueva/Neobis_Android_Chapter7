package com.example.neobis_android_chapter7.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neobis_android_chapter7.MainActivity
import com.example.neobis_android_chapter7.R
import com.example.neobis_android_chapter7.api.Repository
import com.example.neobis_android_chapter7.databinding.FragmentStartBinding
import com.example.neobis_android_chapter7.viewModel.MyViewModel
import com.example.neobis_android_chapter7.viewModel.ViewModelProviderFactory


class StartFragment : Fragment() {

   private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_startFragment_to_authorizationFragment)
        }, 5000)

        return binding.root
    }

}