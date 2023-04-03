package com.example.catchthecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.catchthecat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var runnable:Runnable = Runnable{}
    var handler: Handler = Handler()

    var score=0
    var time=5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        time()
    }
    fun increaseScore(view:View){
        binding.textViewScore.text="Score: ${++score}"
    }
    fun time(){
        runnable = object : Runnable{
            override fun run() {
                time-=1
                binding.textViewTime.text="Time: $time"
                if (time==0){
                    alert()
                }
                else{
                    handler.postDelayed(this,1000)
                }
            }
        }
        handler.post(runnable)
    }
    fun alert(){
        val alert=AlertDialog.Builder(this)
        alert.setTitle("Time is Over!")
        alert.setMessage("Restart the game?")
        alert.setNegativeButton("No"){dialog, which ->
            Toast.makeText(applicationContext,"Game over.",Toast.LENGTH_LONG).show()
            binding.buttonRestart.visibility=View.VISIBLE
        }
        alert.setPositiveButton("Yes"){dialog, which ->
            time=5
            score=0
            time()
        }
        alert.show()
    }
    fun restart(view: View){
        time=5
        time()
    }

}