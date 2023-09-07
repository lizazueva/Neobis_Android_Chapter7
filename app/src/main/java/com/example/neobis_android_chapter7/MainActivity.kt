package com.example.neobis_android_chapter7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.neobis_android_chapter7.api.Repository
import com.example.neobis_android_chapter7.databinding.ActivityMainBinding
import com.example.neobis_android_chapter7.viewModel.MyViewModel
import com.example.neobis_android_chapter7.viewModel.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = ViewModelProviderFactory(repository)
        myViewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
    }
}