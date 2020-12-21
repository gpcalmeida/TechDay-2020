package com.devcamp.tv.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.devcamp.tv.*
import com.devcamp.tv.databinding.ActivityMainBinding
import com.devcamp.tv.ui.main.model.Match
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, View.OnClickListener {
    lateinit var binding: ActivityMainBinding

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
        setExoPlayer()
        setupMatchRecyclerAdapter()
    }

    private fun setExoPlayer() {
        val exoPlayer = SimpleExoPlayer.Builder(this).build()
        with(exoPlayer) {

            val mediaItem = MediaItem.Builder()
                .setUri(getVideoResourcePath(R.raw.acg_int))
                .build()

            this.addMediaItem(mediaItem)
            this.prepare()
            this.play()

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
                binding.matchesRecyclerView.visibility = View.INVISIBLE
            }
            else -> binding.matchesRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onClick(view: View?) {
        view?.run {
            when (id) {

            }
        }
    }

    private fun setupMatchRecyclerAdapter() {
        val matches = listOf(
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0),
            Match(homeTeam = "FLA", homeScore = 1, visitorTeam = "VAS", visitorScore = 0)
        )
        binding.matchesRecyclerView.adapter = MatchRecyclerAdapter(matches).apply {
            onItemClickListener = {
                Toast.makeText(this@MainActivity, "CLICOOOOOOOOOOU", Toast.LENGTH_SHORT).show()
            }
        }
    }
}