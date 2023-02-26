package com.example.beersguide.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.beersguide.MainActivity
import com.example.beersguide.databinding.ActivityMealBinding
import com.example.beersguide.databinding.ActivitySplashBinding
import com.example.beersguide.fragments.HomeFragment

class SplashActivity:AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countDownTimer: CountDownTimer = object : CountDownTimer(2500, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                val intent= Intent(this@SplashActivity,MainActivity::class.java)
                 startActivity(intent)
            }
        }
        countDownTimer.start()
    }

}