package com.devcamp.tv.ui.splash

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import com.devcamp.tv.Navigator
import com.devcamp.tv.R

class SplashActivity : Activity(){
    companion object {
        private const val SPLASH_DURATION = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
    }

    public override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            Navigator(this).goToHomeActivity()
        }, SPLASH_DURATION.toLong())
    }
}