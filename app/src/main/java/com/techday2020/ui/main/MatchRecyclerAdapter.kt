package com.techday2020.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.techday2020.databinding.ItemMatchBinding
import com.techday2020.ui.model.Match

class MatchRecyclerAdapter(
    private var matches: List<Match>
) : RecyclerView.Adapter<MatchRecyclerAdapter.Holder>() {

    lateinit var onItemClickListener : (match: Match) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder (
            ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent,false),
            onItemClickListener
        )
    }

    override fun getItemCount(): Int {
        return matches.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(matches[position])
    }

    class Holder(
        private val binding: ItemMatchBinding,
        private val onItemClickListener : (match : Match) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(match: Match){
            binding.homeTeamTextView.text = match.homeTeam
            binding.homeScoreTextView.text = match.homeScore.toString()
            binding.homeTeamImageView.setImageDrawable(ContextCompat.getDrawable(binding.root.context, match.homeDrawable))

            binding.visitorTeamTextView.text = match.visitorTeam
            binding.visitorScoreTextView.text = match.visitorScore.toString()
            binding.visitorTeamImageView.setImageDrawable(ContextCompat.getDrawable(binding.root.context, match.visitorDrawable))

            binding.root.setOnClickListener {
                onItemClickListener.invoke(match)
            }
        }
    }
}
