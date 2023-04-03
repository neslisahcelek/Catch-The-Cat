package com.example.catchthecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.catchthecat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var runnable: Runnable{}
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun moveCat(view:View){

    }
    fun time(view: View){

    }
    fun score(view: View){

    }
}