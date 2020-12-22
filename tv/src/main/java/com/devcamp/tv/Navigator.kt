package com.devcamp.tv

import android.app.Activity
import android.content.Intent
import com.devcamp.tv.ui.main.MainActivity

class Navigator(var activity: Activity?) {

    fun goToHomeActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        activity?.startActivity(intent)
    }
}