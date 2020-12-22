package com.techday2020.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import network.MatchService

class MainControllerFactory(private val matchesService: MatchService) : Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainController(matchesService) as T
    }
}