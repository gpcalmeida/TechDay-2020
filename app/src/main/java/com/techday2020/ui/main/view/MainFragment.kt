package com.techday2020.ui.main.view

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.techday2020.R
import com.techday2020.databinding.MainFragmentBinding
import com.techday2020.ui.main.MainController
import com.techday2020.ui.main.MainControllerFactory
import com.techday2020.ui.main.view.adapter.MatchRecyclerAdapter
import com.techday2020.ui.model.Match

class MainFragment : Fragment() {

    companion object {
        private const val FILL_HEIGHT_ASPECT_RATIO = 0
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<MainController> {
        MainControllerFactory()
    }

    private val matchesAdapter = MatchRecyclerAdapter(emptyList())

    private lateinit var exoplayer : SimpleExoPlayer
    private lateinit var binding: MainFragmentBinding
    private lateinit var controller: MainController

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
        controller = ViewModelProvider(this).get(MainController::class.java)

        addMediaToPlayer(R.raw.acg_int)

        binding.playImageView.setOnClickListener {
            playVideo()
        }

        setupMatchRecyclerAdapter()
        setupObservers()
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

    private fun setupObservers() {
        viewModel.getMatches().observe(viewLifecycleOwner, Observer { matches ->
            matches?.let {
                matchesAdapter.replaceMatches(it)
            }
        })
    }

    private fun addMediaToPlayer(res: Int) {
        exoplayer = SimpleExoPlayer.Builder(this.requireContext()).build()
        with(exoplayer) {
            val mediaItem = MediaItem.Builder()
                .setUri(getVideoResourcePath(res))
                .setMediaId(res.toString())
                .build()

            this.addMediaItem(mediaItem)
            this.prepare()

            binding.playerView.player = exoplayer
        }
    }

    private fun setupMatchRecyclerAdapter() {
        binding.matchRecyclerView.adapter = matchesAdapter.apply {
            onItemClickListener = {
                addMediaToPlayer(R.raw.bah_sao)
                playVideo()
            }

        }
    }

    private fun getVideoResourcePath(res: Int): String {
        return "android.resource://${this@MainFragment.requireActivity().packageName}/${res}"
    }

    private fun playVideo() {
        binding.playerView.foreground = null
        binding.playImageView.visibility = View.GONE
        exoplayer.play()
    }
}