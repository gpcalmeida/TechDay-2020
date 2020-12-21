package com.techday2020.ui.main

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.techday2020.R
import com.techday2020.databinding.MainFragmentBinding
import com.techday2020.ui.model.Match

class MainFragment : Fragment() {

    companion object {
        private const val FILL_HEIGHT_ASPECT_RATIO = 0
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

        setupPlayer(R.raw.acg_int)
        setupMatchRecyclerAdapter()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {

                binding.playerView.apply {
                    layoutParams = ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    useController = true
                }
            }
            else -> {
                binding.playerView.apply {
                    layoutParams = ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        FILL_HEIGHT_ASPECT_RATIO
                    )
                    useController = false
                }
            }
        }
    }

    private fun setupPlayer(res: Int) {
        val exoPlayer = SimpleExoPlayer.Builder(this.requireContext()).build()
        with(exoPlayer) {

            val mediaItem = MediaItem.Builder()
                .setUri(getVideoResourcePath(res))
                .build()

            this.addMediaItem(mediaItem)
            this.prepare()
            this.play()

            binding.playerView.player = this
        }
    }

    private fun setupMatchRecyclerAdapter() {
        val matches = listOf(
            Match(
                homeTeam = "CAM",
                homeScore = 2,
                homeDrawable = R.drawable.ic_cam,
                visitorTeam = "BOT",
                visitorScore = 1,
                visitorDrawable = R.drawable.ic_bot,
                videoDrawable = R.raw.acg_int
            ),
           Match(
                homeTeam = "ACG",
                homeScore = 0,
                homeDrawable = R.drawable.ic_acg,
                visitorTeam = "INT",
                visitorScore = 0,
                visitorDrawable = R.drawable.ic_int,
                videoDrawable = R.raw.acg_int
            ),
            Match(
                homeTeam = "BAH",
                homeScore = 1,
                homeDrawable = R.drawable.ic_bah,
                visitorTeam = "SAO",
                visitorScore = 3,
                visitorDrawable = R.drawable.ic_sao,
                videoDrawable = R.raw.bah_sao
            ),
            Match(
                homeTeam = "CFC",
                homeScore = 0,
                homeDrawable = R.drawable.ic_cfc,
                visitorTeam = "COR",
                visitorScore = 1,
                visitorDrawable = R.drawable.ic_cor,
                videoDrawable = R.raw.acg_int
            ),
            Match(
                homeTeam = "FLU",
                homeScore = 0,
                homeDrawable = R.drawable.ic_flu,
                visitorTeam = "BGT",
                visitorScore = 0,
                visitorDrawable = R.drawable.ic_bgt,
                videoDrawable = R.raw.bah_sao
            ),

            Match(
                homeTeam = "FOR",
                homeScore = 1,
                homeDrawable = R.drawable.ic_for,
                visitorTeam = "GOI",
                visitorScore = 1,
                visitorDrawable = R.drawable.ic_goi,
                videoDrawable = R.raw.bah_sao
            ),

            Match(
                homeTeam = "PAL",
                homeScore = 3,
                homeDrawable = R.drawable.ic_pal,
                visitorTeam = "ATL",
                visitorScore = 0,
                visitorDrawable = R.drawable.ic_atl,
                videoDrawable = R.raw.bah_sao
            ),

            Match(
                homeTeam = "SAN",
                homeScore = 1,
                homeDrawable = R.drawable.ic_san,
                visitorTeam = "SPT",
                visitorScore = 1,
                visitorDrawable = R.drawable.ic_spt,
                videoDrawable = R.raw.bah_sao
            ),

            Match(
                homeTeam = "VAS",
                homeScore = 1,
                homeDrawable = R.drawable.ic_vas,
                visitorTeam = "CEA",
                visitorScore = 4,
                visitorDrawable = R.drawable.ic_cea,
                videoDrawable = R.raw.bah_sao
            )
        )
        binding.matchRecyclerView.adapter = MatchRecyclerAdapter(matches).apply {
            onItemClickListener = {
                setupPlayer(R.raw.bah_sao)
            }

        }
    }

    private fun getVideoResourcePath(res: Int): String {
        return "android.resource://${this@MainFragment.requireActivity().packageName}/${res}"
    }
}