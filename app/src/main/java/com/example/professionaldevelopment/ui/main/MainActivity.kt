package com.example.professionaldevelopment.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.professionaldevelopment.R
import com.example.professionaldevelopment.databinding.ActivityMainBinding
import com.example.professionaldevelopment.ui.fragments.MainScreenFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainScreenFragment())
                .commit()
        }
    }
}