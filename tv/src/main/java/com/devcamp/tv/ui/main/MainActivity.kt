package com.devcamp.tv.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.devcamp.tv.R
import com.devcamp.tv.databinding.ActivityMainBinding
import com.devcamp.tv.ui.main.model.Match
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    private lateinit var exoPlayer: SimpleExoPlayer

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.matchPlayer.onFocusChangeListener = this
        binding.matchesRecyclerView.onFocusChangeListener = this
        binding.mainContainer.onFocusChangeListener = this
        binding.matchesRecyclerView.visibility = View.INVISIBLE
        setupPlayer()
        setupMatchRecyclerAdapter()
    }

    private fun setupPlayer() {
        exoPlayer = SimpleExoPlayer.Builder(this).build()
        exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
        binding.matchPlayer.player = exoPlayer
        addMediaToPlayer(R.raw.cam_bot)
        exoPlayer.play()
    }

    private fun addMediaToPlayer(res: Int) {
        with(exoPlayer) {

            val mediaItem = MediaItem.Builder()
                .setUri(getVideoResourcePath(res))
                .build()

            this.addMediaItem(mediaItem)
            this.prepare()
            this.next()

            binding.matchPlayer.requestFocus()
            binding.matchPlayer.player = this
        }
    }

    private fun getVideoResourcePath(res: Int): String {
        return "android.resource://${this.packageName}/${res}"
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        when {
            view?.id == R.id.matchPlayer && hasFocus -> {
                val shouldAnimate = !binding.matchesRecyclerView.isInvisible
                binding.matchesRecyclerView.visibility = View.INVISIBLE
                if (shouldAnimate) animateSlideDown(binding.matchesRecyclerView)
            }
            view?.id == R.id.mainContainer -> {
                binding.matchesRecyclerView.requestFocus()
                val shouldAnimate = !binding.matchesRecyclerView.isVisible
                binding.matchesRecyclerView.visibility = View.VISIBLE
                if (shouldAnimate) animateSlideUp(binding.matchesRecyclerView)
            }
            else -> {
                val shouldAnimate = !binding.matchesRecyclerView.isVisible
                binding.matchesRecyclerView.visibility = View.VISIBLE
                if (shouldAnimate) animateSlideUp(binding.matchesRecyclerView)
            }
        }
    }

    override fun onClick(view: View?) {
        view?.run {
            when (id) {

            }
        }
    }

    private fun animateSlideUp(view: View) {
        val translateAnimation =  TranslateAnimation(0f, 0f, view.height.toFloat(), 0f)
        translateAnimation.duration = 200
        translateAnimation.fillAfter = true
        view.startAnimation(translateAnimation)
    }

    private fun animateSlideDown(view: View) {
        val translateAnimation =  TranslateAnimation(0f, 0f, 0f, view.height.toFloat())
        translateAnimation.duration = 200
        translateAnimation.fillAfter = true
        view.startAnimation(translateAnimation)
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
                videoDrawable = R.raw.cam_bot
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
                videoDrawable = R.raw.cfc_cor
            ),
            Match(
                homeTeam = "FLU",
                homeScore = 0,
                homeDrawable = R.drawable.ic_flu,
                visitorTeam = "BGT",
                visitorScore = 0,
                visitorDrawable = R.drawable.ic_bgt,
                videoDrawable = R.raw.flu_bgt
            ),

            Match(
                homeTeam = "FOR",
                homeScore = 1,
                homeDrawable = R.drawable.ic_for,
                visitorTeam = "GOI",
                visitorScore = 1,
                visitorDrawable = R.drawable.ic_goi,
                videoDrawable = R.raw.for_goi
            ),

            Match(
                homeTeam = "PAL",
                homeScore = 3,
                homeDrawable = R.drawable.ic_pal,
                visitorTeam = "ATL",
                visitorScore = 0,
                visitorDrawable = R.drawable.ic_atl,
                videoDrawable = R.raw.pal_atl
            ),

            Match(
                homeTeam = "SAN",
                homeScore = 1,
                homeDrawable = R.drawable.ic_san,
                visitorTeam = "SPT",
                visitorScore = 1,
                visitorDrawable = R.drawable.ic_spt,
                videoDrawable = R.raw.san_spt
            ),

            Match(
                homeTeam = "VAS",
                homeScore = 1,
                homeDrawable = R.drawable.ic_vas,
                visitorTeam = "CEA",
                visitorScore = 4,
                visitorDrawable = R.drawable.ic_cea,
                videoDrawable = R.raw.vas_cea
            )
        )
        binding.matchesRecyclerView.adapter = MatchRecyclerAdapter(matches).apply {
            onItemClickListener = {
                addMediaToPlayer(it.videoDrawable)
                exoPlayer.play()
            }
        }
    }
}