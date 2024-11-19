package com.mricoism.fragmentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.fragmentapp.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "MainActivityRIKO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}

/*
To Create new fragment you can do :
 double tap inside folder com.mricoism.fragmentapp
 new > Fragment > chose fragment


 */
