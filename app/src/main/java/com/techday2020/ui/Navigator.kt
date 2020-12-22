package com.techday2020.ui

import android.app.Activity
import android.content.Intent
import com.techday2020.ui.main.view.MainActivity

class Navigator(var activity: Activity?) {

    fun goToHomeActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        activity?.startActivity(intent)
    }
}