package com.devcamp.tv.ui.home.tracks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devcamp.tv.Navigator
import com.devcamp.tv.R
import com.devcamp.tv.ui.home.movies.MovieClickListener
import com.devcamp.tv.ui.home.movies.MoviesAdapter
import kotlinx.android.synthetic.main.item_recycler_home_tracks.view.*

class TracksAdapter(private val listener: MovieClickListener) : RecyclerView.Adapter<TracksAdapter.ViewHolder>() {
    private val tracks : MutableList<TracksViewModel> = mutableListOf()
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_home_tracks,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = tracks[position]
        holder.textView.text = track.trackTitle

        val childLayoutManager = LinearLayoutManager( holder.recyclerView.context,
            LinearLayout.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = 4

        holder.recyclerView.apply {
            setHasFixedSize(true)
            adapter = MoviesAdapter(context, listener).apply {
                    update(track.movies)
                }

            layoutManager = childLayoutManager
            setRecycledViewPool(viewPool)
        }
    }

    fun update(tracksList: MutableList<TracksViewModel>){
        this.tracks.clear()
        this.tracks.addAll(tracksList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val recyclerView : RecyclerView = itemView.tracksList
        val textView: TextView = itemView.categoryName
    }
}