package com.devcamp.tv

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginStart

var lastClickTime: Long = 0

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Activity.unselectedViews(vararg views: View) {
    for (view in views) {
        view.isSelected = false
    }
}

fun Activity.whichViewIsSelected(vararg views: View): Int {
    for (view in views) {
        if (view.isSelected) {
            return view.id
        }
    }
    return 0
}

fun View.focusDelayed(): View {
    Handler().postDelayed({ requestFocus() }, 100)
    return this
}

fun View.selected(): View {
    isSelected = true
    return this
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun View.expand() {
    elevation = 2f
    animate().scaleX(1.1f).scaleY(1.1f).translationY(-8.dp.toFloat()).setDuration(200).start()

    (layoutParams as ViewGroup.MarginLayoutParams).setMargins(16.dp, 16.dp, 16.dp, 16.dp)
    this.invalidate()
    this.requestLayout()
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun View.reduce() {
    elevation = 0f
    animate().scaleX(1.0f).scaleY(1.0f).translationY(8.dp.toFloat()).setDuration(200).start()
    (layoutParams as ViewGroup.MarginLayoutParams).setMargins(8.dp, 8.dp, 8.dp, 8.dp)

    this.invalidate()
    this.requestLayout()
}

inline fun Context.preventMultipleClick(action: () -> Unit) {
    if (SystemClock.elapsedRealtime() - lastClickTime < 800) {
        return
    }
    lastClickTime = SystemClock.elapsedRealtime()
    action()
}