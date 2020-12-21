package com.devcamp.tv.ui.home.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.devcamp.tv.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(val context : Context, val listener : MovieClickListener)
    : RecyclerView.Adapter<MoviesAdapter.ViewHolder>(), View.OnFocusChangeListener{

    private val movies : MutableList<MoviesViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie,parent,false)

    view.isFocusable = true
    view.isFocusableInTouchMode = true
    view.onFocusChangeListener = this
    view.isFocused


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        ImageLoader.loadFitCenter(context,
            movie.cardImage,
            R.drawable.placeholder_track_item,
            holder.imageView)


        holder.imageView.setOnClickListener {
                listener.onClickMovie(movie)
            }
    }

    fun update(movies: MutableList<MoviesViewModel>){
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if(view.isFocused){
            view.expand()
            view.posterForeground.visibility = View.GONE
        } else {
            view.reduce()
            view.posterForeground.visibility = View.VISIBLE
        }
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.poster
        val foregroundPoster : ImageView = itemView.posterForeground
    }
}