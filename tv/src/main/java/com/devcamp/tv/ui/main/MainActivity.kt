package com.devcamp.tv.ui.main

import android.os.Bundle
import android.transition.TransitionManager
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.devcamp.tv.*
import com.devcamp.tv.ui.account.AccountFragment
import com.devcamp.tv.ui.account.AccountFragment.Companion.ACCOUNT_TAG
import com.devcamp.tv.ui.home.HomeFragment
import com.devcamp.tv.ui.home.HomeFragment.Companion.HOME_TAG
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener,View.OnClickListener {
    private var isFirstTime: Boolean = true

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        unselectedViews(activity_main_text_view_account)

        activity_main_text_view_home.setOnClickListener(this)
        activity_main_text_view_account.setOnClickListener(this)

        activity_main_text_view_home.onFocusChangeListener = this
        activity_main_text_view_account.onFocusChangeListener = this

        activity_main_text_view_home.selected()
        activity_main_text_view_home.requestFocus()
        onDestinationSelected(HOME_TAG)

        openDrawer()    }

    override fun onBackPressed() {
        val viewSelected = whichViewIsSelected(activity_main_text_view_home, activity_main_text_view_account)

        if (!drawerIsOpen()) {
            openDrawer()

            when (viewSelected) {
                R.id.activity_main_text_view_home -> {
                    (supportFragmentManager.findFragmentByTag(HOME_TAG) as? HomeFragment)?.drawerOpen()
                    activity_main_text_view_home.focusDelayed()
                }

                R.id.activity_main_text_view_account -> {
                    (supportFragmentManager.findFragmentByTag(ACCOUNT_TAG) as? AccountFragment)
                    activity_main_text_view_home.focusDelayed()
                }
            }
            return
        }

        closeDrawer()
        when (viewSelected) {
            R.id.activity_main_text_view_home -> {
                activity_main_text_view_home.clearFocus()
                (supportFragmentManager.findFragmentByTag(HOME_TAG) as? HomeFragment)?.drawerClosed()
            }

            R.id.activity_main_text_view_account -> {
                activity_main_text_view_account.clearFocus()
                (supportFragmentManager.findFragmentByTag(ACCOUNT_TAG) as? AccountFragment)
            }
        }
    }

    private fun openDrawer() {
        activity_main_content_menu.layoutParams = FrameLayout.LayoutParams(resources.getDimensionPixelSize(R.dimen.spacings_two_hundred_fifty), FrameLayout.LayoutParams.MATCH_PARENT)
        activity_main_container.foreground = ContextCompat.getDrawable(baseContext,
            R.color.blackFortyTransparency
        )
        TransitionManager.beginDelayedTransition(activity_main_content_menu)
    }

    private fun closeDrawer() {
        activity_main_content_menu.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT)
        activity_main_container.foreground = null
        TransitionManager.beginDelayedTransition(activity_main_content_menu)
    }


    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        when (event?.keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                finish()
            }
        }
        return super.onKeyLongPress(keyCode, event)
    }

    private fun drawerIsOpen() = activity_main_content_menu.layoutParams.width == resources.getDimensionPixelSize(R.dimen.spacings_two_hundred_fifty)

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        when (view?.id) {
            R.id.activity_main_text_view_home,
            R.id.activity_main_text_view_account -> {
                if (!isFirstTime) {
                    if (hasFocus) {
                        openDrawer()
                        return
                    }

                    closeDrawer()
                } else {
                    isFirstTime = false
                }
            }
        }
    }

    override fun onClick(view: View?) {
        view?.run {
            when (id) {
                R.id.activity_main_text_view_home -> {
                    unselectedViews(activity_main_text_view_account)
                    selected()
                    onDestinationSelected(HOME_TAG)
                }

                R.id.activity_main_text_view_account -> {
                    unselectedViews(activity_main_text_view_home)
                    selected()
                    onDestinationSelected(ACCOUNT_TAG)
                }
            }
            closeDrawer()
        }
    }
}