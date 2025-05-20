package com.tassos.superpodcast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PodcastViewModel : ViewModel() {

    private val _episodes = MutableStateFlow<List<Episode>>(emptyList())
    val episodes: StateFlow<List<Episode>> = _episodes.asStateFlow()

    private val _podcasts = MutableStateFlow<List<PodCast>>(emptyList())
    val podcasts: StateFlow<List<PodCast>> = _podcasts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadEpisodes(feedUrl: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                // Find the podcast by feedUrl to get realistic episode data
                val podcast = _podcasts.value.find { it.feedUrl == feedUrl }
                val podcastTitle = podcast?.title ?: "Unknown Podcast"

                // Generate episodes based on the podcast
                val sampleEpisodes = generateEpisodesForPodcast(podcastTitle, feedUrl)

                _episodes.value = sampleEpisodes
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadPodcasts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                // Realistic podcast data with proper image URLs
                val samplePodcasts = listOf(
                    PodCast(
                        title = "The Joe Rogan Experience",
                        feedUrl = "https://feeds.megaphone.fm/JOE1387556632",
                        imageUrl = "https://images.unsplash.com/photo-1478737270239-2f02b77fc618?w=400",
                        description = "The official podcast of comedian Joe Rogan. Long-form conversations with a wide range of guests.",
                        author = "Joe Rogan",
                        category = "Comedy"
                    ),
                    PodCast(
                        title = "Serial",
                        feedUrl = "https://feeds.serialpodcast.org/serial",
                        imageUrl = "https://images.unsplash.com/photo-1590602847861-f357a9332bbc?w=400",
                        description = "Serial tells one story - a true story - over the course of an entire season.",
                        author = "Sarah Koenig",
                        category = "True Crime"
                    ),
                    PodCast(
                        title = "The Daily",
                        feedUrl = "https://feeds.nytimes.com/nyt/rss/podcasts/the-daily",
                        imageUrl = "https://images.unsplash.com/photo-1504711434969-e33886168f5c?w=400",
                        description = "The biggest stories of our time, told by the best journalists in the world.",
                        author = "The New York Times",
                        category = "News"
                    ),
                    PodCast(
                        title = "Conan O'Brien Needs a Friend",
                        feedUrl = "https://feeds.megaphone.fm/conan",
                        imageUrl = "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=400",
                        description = "Conan O'Brien has been working in television for over 25 years, but he's never had a friend.",
                        author = "Conan O'Brien",
                        category = "Comedy"
                    ),
                    PodCast(
                        title = "TED Talks Daily",
                        feedUrl = "https://feeds.feedburner.com/tedtalks_audio",
                        imageUrl = "https://images.unsplash.com/photo-1475721027785-f74eccf877e2?w=400",
                        description = "Every weekday, TED Talks Daily brings you the latest talks in audio form.",
                        author = "TED",
                        category = "Education"
                    ),
                    PodCast(
                        title = "This American Life",
                        feedUrl = "https://feeds.thisamericanlife.org/talpodcast",
                        imageUrl = "https://images.unsplash.com/photo-1532635270-c2d9235d8213?w=400",
                        description = "Stories of life in America, told with emotion, humor, and surprising turns.",
                        author = "Ira Glass",
                        category = "Society & Culture"
                    ),
                    PodCast(
                        title = "Radiolab",
                        feedUrl = "https://feeds.feedburner.com/radiolab",
                        imageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=400",
                        description = "Investigation and mystery, told through compelling narrative and stunning sound design.",
                        author = "WNYC Studios",
                        category = "Science"
                    ),
                    PodCast(
                        title = "Crime Junkie",
                        feedUrl = "https://feeds.audioboom.com/channels/4901482.rss",
                        imageUrl = "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=400",
                        description = "If you can never get enough true crime... You've found your people.",
                        author = "Ashley Flowers",
                        category = "True Crime"
                    )
                )

                _podcasts.value = samplePodcasts
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun generateEpisodesForPodcast(podcastTitle: String, feedUrl: String): List<Episode> {
        return when (podcastTitle) {
            "The Joe Rogan Experience" -> listOf(
                Episode(
                    title = "#2000 - Dr. Jordan Peterson",
                    description = "Jordan Peterson is a clinical psychologist, author, and professor. His new book 'We Who Wrestle with God' is available now.",
                    author = "Joe Rogan",
                    imageUrl = "https://images.unsplash.com/photo-1478737270239-2f02b77fc618?w=400",
                    feedUrl = feedUrl,
                    duration = "3:24:15"
                ),
                Episode(
                    title = "#1999 - Elon Musk",
                    description = "Elon Musk is the CEO of Tesla, SpaceX, and now X (formerly Twitter). He discusses his latest ventures and visions for the future.",
                    author = "Joe Rogan",
                    imageUrl = "https://images.unsplash.com/photo-1478737270239-2f02b77fc618?w=400",
                    feedUrl = feedUrl,
                    duration = "2:45:32"
                )
            )
            "Serial" -> listOf(
                Episode(
                    title = "Episode 01: The Alibi",
                    description = "It's Baltimore, 1999. Hae Min Lee, a popular high-school senior, disappears after school one day. Six weeks later detectives arrest her ex-boyfriend, Adnan Syed, for her murder.",
                    author = "Sarah Koenig",
                    imageUrl = "https://images.unsplash.com/photo-1590602847861-f357a9332bbc?w=400",
                    feedUrl = feedUrl,
                    duration = "53:12"
                ),
                Episode(
                    title = "Episode 02: The Breakup",
                    description = "Adnan and Hae's relationship was typical teenage stuff: they met in health class, got together, broke up, got back together, broke up again.",
                    author = "Sarah Koenig",
                    imageUrl = "https://images.unsplash.com/photo-1590602847861-f357a9332bbc?w=400",
                    feedUrl = feedUrl,
                    duration = "45:38"
                )
            )
            "The Daily" -> listOf(
                Episode(
                    title = "Thursday, December 19, 2024",
                    description = "Today's biggest stories in 20 minutes, from the team at The New York Times.",
                    author = "Michael Barbaro",
                    imageUrl = "https://images.unsplash.com/photo-1504711434969-e33886168f5c?w=400",
                    feedUrl = feedUrl,
                    duration = "22:15"
                ),
                Episode(
                    title = "Wednesday, December 18, 2024",
                    description = "The latest on international markets and their impact on the global economy.",
                    author = "Sabrina Tavernise",
                    imageUrl = "https://images.unsplash.com/photo-1504711434969-e33886168f5c?w=400",
                    feedUrl = feedUrl,
                    duration = "25:43"
                )
            )
            else -> listOf(
                Episode(
                    title = "Episode 1: Getting Started",
                    description = "Welcome to $podcastTitle! In this first episode, we introduce ourselves and discuss what's coming up.",
                    author = "Host",
                    imageUrl = "https://images.unsplash.com/photo-1478737270239-2f02b77fc618?w=400",
                    feedUrl = feedUrl,
                    duration = "30:00"
                ),
                Episode(
                    title = "Episode 2: Deep Dive",
                    description = "In this episode, we take a deep dive into the topic that started it all.",
                    author = "Host",
                    imageUrl = "https://images.unsplash.com/photo-1478737270239-2f02b77fc618?w=400",
                    feedUrl = feedUrl,
                    duration = "45:00"
                )
            )
        }
    }

    fun clearError() {
        _error.value = null
    }
}