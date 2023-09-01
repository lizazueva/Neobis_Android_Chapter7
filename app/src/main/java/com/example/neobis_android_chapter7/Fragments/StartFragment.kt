package com.example.neobis_android_chapter7.Fragments

import android.os.Bundle
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
   lateinit var viewModelStartFragment: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelStartFragment = (activity as MainActivity).myViewModel

        binding.buttonStart.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_registrationFragment)
        }

        binding.textEnter.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_authorizationFragment)
        }

    }
}