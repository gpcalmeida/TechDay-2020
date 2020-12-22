package com.techday2020.ui.main.view

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.devcamp.network.MatchServiceImpl
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.techday2020.R
import com.techday2020.databinding.MainFragmentBinding
import com.techday2020.ui.main.MainController
import com.techday2020.ui.main.MainControllerFactory
import com.techday2020.ui.main.view.adapter.MatchRecyclerAdapter

class MainFragment : Fragment() {

    companion object {
        private const val FILL_HEIGHT_ASPECT_RATIO = 0
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<MainController> {
        MainControllerFactory()
    }

    private val matchesAdapter = MatchRecyclerAdapter(emptyList())

    private lateinit var exoplayer: SimpleExoPlayer
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

        setupMatchRecyclerAdapter()
        setupListeners()
        setupObservers()
        setupPlayer()

        addMediaToPlayer(R.raw.acg_int)
    }

    override fun onPause() {
        exoplayer.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        exoplayer.play()
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
                }

                binding.fullscreenImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.exo_controls_fullscreen_exit
                    )
                )
            }
            else -> {
                binding.playerView.apply {
                    layoutParams = ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        FILL_HEIGHT_ASPECT_RATIO
                    )
                }

                binding.fullscreenImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.exo_controls_fullscreen_enter
                    )
                )
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            playTapumeImageView.setOnClickListener {
                playVideo()
            }

            playImageView.setOnClickListener {
                if (exoplayer.isPlaying) {
                    playImageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.exo_icon_play
                        )
                    )
                    exoplayer.pause()
                } else {
                    playImageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.exo_icon_pause
                        )
                    )
                    exoplayer.play()
                }
            }

            fullscreenImageView.setOnClickListener {
                with(requireActivity().requestedOrientation) {
                    when (this) {
                        ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE -> {
                            requireActivity().requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

                            fullscreenImageView.setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.exo_controls_fullscreen_enter
                                )
                            )

                            requireActivity().requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
                        }
                        else -> {
                            requireActivity().requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

                            fullscreenImageView.setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.exo_controls_fullscreen_exit
                                )
                            )
                        }
                    }
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

    private fun setupPlayer() {
        exoplayer = SimpleExoPlayer.Builder(this@MainFragment.requireContext()).build()
        with(exoplayer) {
            repeatMode = Player.REPEAT_MODE_ALL
            binding.playerView.player = this
        }.also {
            binding.playerView.useController = false
        }
    }

    private fun addMediaToPlayer(res: Int) {
        with(exoplayer) {
            val mediaItem = MediaItem.Builder()
                .setUri(getVideoResourcePath(res))
                .setMediaId(res.toString())
                .build()

            this.addMediaItem(mediaItem)
            this.prepare()
            this.next()
        }
    }

    private fun setupMatchRecyclerAdapter() {
        binding.matchRecyclerView.adapter = matchesAdapter.apply {
            onItemClickListener = {
                addMediaToPlayer(it.videoDrawable)
                playVideo()
            }
        }
    }

    private fun playVideo() {
        with(binding) {
            playerView.foreground = null
            playTapumeImageView.visibility = View.GONE
            playImageView.visibility = View.VISIBLE
            fullscreenImageView.visibility = View.VISIBLE
        }
        exoplayer.play()
    }

    private fun getVideoResourcePath(res: Int): String {
        return "android.resource://${this@MainFragment.requireActivity().packageName}/${res}"
    }
}