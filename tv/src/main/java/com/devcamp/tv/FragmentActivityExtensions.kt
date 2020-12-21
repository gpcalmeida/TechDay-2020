package com.devcamp.tv

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.devcamp.tv.ui.home.HomeFragment
import com.devcamp.tv.ui.home.HomeFragment.Companion.HOME_TAG
import com.devcamp.tv.ui.account.AccountFragment


const val ROOT_TAG = "root_fragment"

/**
 * Adds a fragment to the master back stack, allowing retrieval using tag. Should be used for
 * top-level destinations from the bottom navigation view.
 */
fun FragmentActivity.onDestinationSelected(fragmentTag: String) {
    supportFragmentManager.popBackStack(ROOT_TAG, 0)
    supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.activity_main_container, findSelectedFragment(fragmentTag), fragmentTag)
            .addToBackStack(ROOT_TAG)
            .commitAllowingStateLoss()
}

fun FragmentActivity.findSelectedFragment(fragmentTag: String): Fragment {
    return supportFragmentManager.findFragmentByTag(fragmentTag).let {
        if (it == null) return@let when (fragmentTag) {
            HOME_TAG -> HomeFragment.newInstance()
            else -> AccountFragment.newInstance()
        } else {
            return@let it
        }
    }
}

fun FragmentActivity.replaceFragment(fragment: Fragment, container: Int) {
    supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
            .replace(container, fragment)
            .commitAllowingStateLoss()
}
