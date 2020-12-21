package com.techday2020.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory

class MainControllerFactory : Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainController() as T
    }
}