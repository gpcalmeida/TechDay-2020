package com.devcamp.tv.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.devcamp.tv.R
import com.devcamp.tv.databinding.ItemMatchBinding
import com.devcamp.tv.ui.main.model.Match

class MatchRecyclerAdapter(
    private var matches: List<Match>
) : RecyclerView.Adapter<MatchRecyclerAdapter.Holder>(),
    View.OnFocusChangeListener {

    lateinit var onItemClickListener: (Match) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view.root.isFocusable = true
        view.root.onFocusChangeListener = this
        view.root.nextFocusUpId = R.id.matchPlayer
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
        private val onItemClickListener: (Match) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(match: Match) {
            with(binding.root.context) {
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
                onItemClickListener.invoke(match)
            }
        }
    }

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if (view.isFocused) {
            view.background =
                ContextCompat.getDrawable(view.context, R.drawable.dr_selected_match_card)
        } else {
            view.background =
                ContextCompat.getDrawable(view.context, R.drawable.dr_card_match_gradient)
        }
    }
}