package com.techday2020.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.techday2020.R
import com.techday2020.databinding.ItemMatchBinding
import com.techday2020.ui.model.Match

class MatchRecyclerAdapter(
    private var matches: List<Match>
) : RecyclerView.Adapter<MatchRecyclerAdapter.Holder>() {

    var selectedPosition = 0

    lateinit var onItemClickListener: (match: Match) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun getItemCount(): Int {
        return matches.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(matches[position], position == selectedPosition, position)
    }

    inner class Holder(
        private val binding: ItemMatchBinding,
        private val onItemClickListener: (match: Match) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(match: Match, selected: Boolean, pos : Int) {
            with(binding.root.context) {
                binding.matchRootLinearLayout.background =
                    if (selected) {
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.dr_selected_match_card
                        )
                    }
                    else {
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.dr_card_match_gradient
                        )
                    }


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

                binding.root.setOnClickListener {
                    selectedPosition = pos
                    onItemClickListener.invoke(match)
                    notifyDataSetChanged()
                }
            }
        }
    }
}
