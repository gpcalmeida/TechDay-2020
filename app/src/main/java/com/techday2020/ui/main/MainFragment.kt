package com.techday2020.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.exoplayer2.SimpleExoPlayer
import com.techday2020.databinding.MainFragmentBinding
import com.techday2020.ui.model.Match

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val exoPlayer = SimpleExoPlayer.Builder(this.requireContext()).build()

        exoPlayer.play()

        binding.player.player = exoPlayer

        setupMatchRecyclerAdapter()
    }

    private fun setupMatchRecyclerAdapter() {
        val matches = listOf(
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0)
        )
        binding.matchRecyclerView.adapter = MatchRecyclerAdapter(matches).apply {
            onItemClickListener = {
                Toast.makeText(requireContext(), "CLICOOOOOOOOOOU", Toast.LENGTH_SHORT).show()
            }
        }
    }

}