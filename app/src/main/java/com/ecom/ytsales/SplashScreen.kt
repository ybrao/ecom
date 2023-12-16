package com.ecom.ytsales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.ecom.ytsales.databinding.ActivityMainBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SplashScreen, R.layout.activity_main)
        binding.appName.text="YT SALES"



        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                var intent = Intent(this@SplashScreen,loginActivity::class.java)
                startActivity(intent)

            }
        },3000)


    }

}