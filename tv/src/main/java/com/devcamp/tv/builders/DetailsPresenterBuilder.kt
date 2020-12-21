package com.devcamp.tv.builders

import com.devcamp.tv.ui.details.DetailsPresenter
import com.devcamp.tv.ui.details.DetailsView

object DetailsPresenterBuilder{
    fun create(view : DetailsView) : DetailsPresenter {
        return DetailsPresenter(view)
    }
}