package com.techday2020.ui.main.view

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.techday2020.databinding.MainFragmentBinding
import com.techday2020.ui.main.MainController
import com.techday2020.ui.main.MainControllerFactory
import com.techday2020.ui.main.view.adapter.MatchRecyclerAdapter
import com.techday2020.ui.model.Info
import network.MatchServiceImpl

class MainFragment : Fragment() {

    companion object {
        private const val FILL_HEIGHT_ASPECT_RATIO = 0
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<MainController> {
        MainControllerFactory(MatchServiceImpl(requireContext()))
    }

    private val matchesAdapter = MatchRecyclerAdapter(emptyList())

    private lateinit var exoplayer: SimpleExoPlayer
    private lateinit var binding: MainFragmentBinding

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
        binding.playImageView.setOnClickListener {
            playVideo()
        }

        setupMatchRecyclerAdapter()
        setupObservers()
        setupPlayer()
        binding.infoImageView.setOnClickListener { showInfo() }
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

                setInfo(it[0].info)
                addMediaToPlayer(getVideoMediaSource(it[0].videoDrawable))
            }
        })
    }

    private fun setupPlayer() {
        exoplayer = SimpleExoPlayer.Builder(this.requireContext()).build()
        exoplayer.repeatMode = Player.REPEAT_MODE_ALL
        binding.playerView.player = exoplayer

    }

    private fun addMediaToPlayer(mediaSource: MediaSource) {
        with(exoplayer) {
            this.setMediaSource(mediaSource)
            this.prepare()
            this.next()
        }
    }

    private fun setupMatchRecyclerAdapter() {
        binding.matchRecyclerView.adapter = matchesAdapter.apply {
            onItemClickListener = {
                addMediaToPlayer(getVideoMediaSource(it.videoDrawable))

                setInfo(it.info)

                playVideo()
            }
        }
    }

    private fun playVideo() {
        binding.playerView.foreground = null
        binding.playImageView.visibility = View.GONE
        exoplayer.play()
    }

    private fun getVideoMediaSource(path: String): MediaSource {
        val dataSourceFactory: DataSource.Factory =
            DataSource.Factory { AssetDataSource(requireActivity()) }

        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(
                MediaItem.Builder().setUri(Uri.parse("assets:///matches/$path"))
                    .build()
            )
    }

    private fun showInfo() {
        if (binding.infoContainer.isVisible) {
            binding.infoContainer.visibility = View.GONE
        } else {
            binding.infoContainer.visibility = View.VISIBLE
        }
    }

    private fun setInfo(info: Info?) {
        if (info != null) {
            binding.infoImageView.visibility = View.VISIBLE
            binding.infoTitleTextView.text = info.title
            binding.descriptionTextView.text = info.description
        } else {
            binding.infoImageView.visibility = View.GONE
        }
    }
}