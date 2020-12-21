package com.devcamp.tv.builders

import android.app.Activity
import com.devcamp.tv.Navigator
import com.devcamp.tv.ui.home.HomePresenter
import com.devcamp.tv.ui.home.HomeView

object HomePresenterBuilder{
    fun create(view : HomeView) : HomePresenter{
        return HomePresenter(view, TracksBuilder.create())
    }
}