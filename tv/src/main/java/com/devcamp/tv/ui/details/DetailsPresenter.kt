package com.devcamp.tv.ui.details

class DetailsPresenter(val view : DetailsView){
    fun onCreate(){
        view.addMovieDetails()
    }
}