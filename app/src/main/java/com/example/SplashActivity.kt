package com.example

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.ott.util.moveActivity
import com.example.admindata.R

import com.example.admindata.UsersList
import com.example.admindata.util.MyPreferences

class SplashActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var myPreferences: MyPreferences

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        context = this
        myPreferences = MyPreferences(context)
        // Add a delay to simulate a splash screen
        Handler(Looper.getMainLooper()).postDelayed({
         val isLogin=   myPreferences.getString("isLogin","")
            if (isLogin=="true"){
                moveActivity(UsersList())
                finishAffinity()
            }else{
                moveActivity(UsersList())
                finishAffinity()
            }

        }, SPLASH_DELAY.toLong())

    }
    companion object {
        private const val SPLASH_DELAY = 1000 // Delay in milliseconds (3 seconds)
    }
}