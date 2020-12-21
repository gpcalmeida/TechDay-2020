package com.devcamp.tv.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devcamp.tv.databinding.ItemMatchBinding
import com.devcamp.tv.expand
import com.devcamp.tv.reduce
import com.devcamp.tv.ui.main.model.Match
import kotlinx.android.synthetic.main.item_movie.view.*

class MatchRecyclerAdapter(
    private var matches: List<Match>
) : RecyclerView.Adapter<MatchRecyclerAdapter.Holder>(),
    View.OnFocusChangeListener {

    lateinit var onItemClickListener: () -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view.root.isFocusable = true
        view.root.isFocusableInTouchMode = true
        view.root.onFocusChangeListener = this
        return Holder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return matches.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(matches[position])
    }

    class Holder(
        private val binding: ItemMatchBinding,
        private val onItemClickListener: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(match: Match) {
            binding.homeTeamTextView.text = match.homeTeam
            binding.homeScoreTextView.text = match.homeScore.toString()
            binding.visitorTeamTextView.text = match.visitorTeam
            binding.visitorScoreTextView.text = match.visitorScore.toString()

            binding.root.setOnClickListener {
                onItemClickListener.invoke()
            }
        }
    }

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if (view.isFocused) {
            view.expand()
        } else {
            view.reduce()
        }
    }
}