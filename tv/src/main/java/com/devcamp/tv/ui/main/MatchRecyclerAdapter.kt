package com.devcamp.tv.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.devcamp.tv.R
import com.devcamp.tv.databinding.ItemMatchBinding
import com.devcamp.tv.expand
import com.devcamp.tv.reduce
import com.devcamp.tv.ui.main.model.Match

class MatchRecyclerAdapter(
    private var matches: List<Match>
) : RecyclerView.Adapter<MatchRecyclerAdapter.Holder>(),
    View.OnFocusChangeListener {

    lateinit var onItemClickListener: (Match) -> Unit
    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view.root.isFocusable = true
        view.root.onFocusChangeListener = this
        view.root.nextFocusUpId = R.id.matchPlayer
        view.root.nextFocusDownId = R.id.matchPlayer
        return Holder(view, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return matches.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(matches[position], position == selectedPosition, position)
    }

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if (view.isFocused) {
            view.expand()
        } else {
            view.reduce()
        }
    }

    inner class Holder(
        private val binding: ItemMatchBinding,
        private val onItemClickListener: (Match) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match, selected: Boolean, pos: Int) {
            with(binding.root.context) {
                binding.root.nextFocusUpId = R.id.matchPlayer
                binding.root.nextFocusDownId = R.id.matchPlayer
                binding.root.isSelected = selected
                binding.root.background =
                    ContextCompat.getDrawable(this, R.drawable.dr_selector_match_item)
                binding.homeTeamTextView.text = match.homeTeam
                binding.homeScoreTextView.text = match.homeScore.toString()
                binding.homeTeamImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        match.homeDrawable
                    )
                )

                binding.visitorTeamTextView.text = match.visitorTeam
                binding.visitorScoreTextView.text = match.visitorScore.toString()
                binding.visitorTeamImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        match.visitorDrawable
                    )
                )
            }

            binding.root.setOnClickListener {
                if (selectedPosition == pos)
                    return@setOnClickListener
                selectedPosition = pos
                onItemClickListener.invoke(match)
                binding.root.isSelected = true
                notifyDataSetChanged()
            }
        }
    }
}