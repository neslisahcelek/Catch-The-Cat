package com.example.catchthecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.catchthecat.databinding.ActivityMainBinding
import java.util.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var runnable:Runnable = Runnable{}
    var handler: Handler = Handler()

    var score=0
    var time=10000

    val catList = ArrayList<ImageView>()
    var index=5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        catVisible()
        time()
    }
    fun catVisible(){
        catList.add(binding.imageViewCat)
        catList.add(binding.imageViewCat2)
        catList.add(binding.imageViewCat3)
        catList.add(binding.imageViewCat4)
        catList.add(binding.imageViewCat5)
        catList.add(binding.imageViewCat6)
        catList.add(binding.imageViewCat7)
        catList.add(binding.imageViewCat8)
        catList.add(binding.imageViewCat9)
        catList.add(binding.imageViewCat10)
        catList.add(binding.imageViewCat11)
        catList.add(binding.imageViewCat12)
        catList.add(binding.imageViewCat13)
        catList.add(binding.imageViewCat14)
        catList.add(binding.imageViewCat15)
        catList.add(binding.imageViewCat16)

        for (cat in catList){
            cat.visibility=View.INVISIBLE
        }
        val random = Random()
        index = random.nextInt(16)
            runnable = object : Runnable{
            override fun run() {
                catList.get(index).visibility=View.INVISIBLE
                index = random.nextInt(16)
                catList.get(index).visibility=View.VISIBLE
                handler.postDelayed(this,500)
            }
        }
        handler.post(runnable)
    }
    //score arttıkça hızı azalt
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
                handler.removeCallbacks(runnable)
                catList.get(index).visibility=View.INVISIBLE
                alert()
            }
        }.start()
    }
    fun alert(){
        val alert=AlertDialog.Builder(this)
        alert.setTitle("Time is Over!")
        alert.setMessage("Restart the game?")
        alert.setNegativeButton("No"){dialog, which ->
            Toast.makeText(applicationContext,"Game over.", Toast.LENGTH_LONG).show()
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