package com.devcamp.tv.ui.account

import androidx.fragment.app.Fragment

class AccountFragment : Fragment(){
    companion object {
        const val ACCOUNT_TAG = "ACCOUNT"
        fun newInstance(): AccountFragment {
            return AccountFragment()
        }
    }
}