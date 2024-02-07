package com.fajar.moviedb.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.fajar.moviedb.MainActivity
import com.fajar.moviedb.R

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private var durasi: Long=2000 //2.5 detik

    private val mRunnable: Runnable = Runnable {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable,durasi)
    }

}