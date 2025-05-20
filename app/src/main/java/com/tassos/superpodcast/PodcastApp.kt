package com.tassos.superpodcast

import androidx.lifecycle.ViewModel

class PodcastApp : ViewModel() {
    // This class can be used to manage the overall podcast application state

    private var _currentPodcast = PodCast(
        title = "",
        feedUrl = "",
        imageUrl = ""
    )
    val currentPodcast: PodCast get() = _currentPodcast

    private var _currentEpisode: Episode? = null
    val currentEpisode: Episode? get() = _currentEpisode

    fun setCurrentPodcast(podcast: PodCast) {
        _currentPodcast = podcast
    }

    fun setCurrentEpisode(episode: Episode) {
        _currentEpisode = episode
    }

    fun clearCurrentEpisode() {
        _currentEpisode = null
    }
}