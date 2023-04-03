package com.example.catchthecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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
    var time=5000

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
        binding.textViewScore.text="Score: $score"
        binding.textViewTime.text="Time: ${time/1000}"
        object :CountDownTimer(time.toLong(),1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.textViewTime.text="Time: ${millisUntilFinished/1000}"
            }
            override fun onFinish() {
                alert()
            }
        }.start()
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
            val intent = intent
            finish()
            startActivity(intent)
        }
        alert.show()
    }
    fun restart(view: View){
        binding.buttonRestart.visibility=View.INVISIBLE
        val intent = intent
        finish()
        startActivity(intent)
    }

}