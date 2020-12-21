package com.techday2020.ui.main.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.techday2020.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    MainFragment.newInstance()
                )
                .commitNow()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                window.addFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
            else -> {
                window.clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )

            }
        }
    }
}