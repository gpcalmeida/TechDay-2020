package com.techday2020.ui

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.techday2020.R

class SplashActivity : Activity(){
    companion object {
        private const val SPLASH_DURATION = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    public override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            Navigator(this).goToHomeActivity()
        }, SPLASH_DURATION.toLong())
    }
}