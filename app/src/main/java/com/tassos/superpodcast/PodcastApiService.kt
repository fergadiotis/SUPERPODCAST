package com.tassos.superpodcast

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface PodcastApiService {

    @GET("search")
    suspend fun searchPodcasts(
        @Query("term") searchTerm: String,
        @Query("media") media: String = "podcast",
        @Query("limit") limit: Int = 50
    ): Response<PodcastSearchResponse>

    @GET("lookup")
    suspend fun getPodcastById(
        @Query("id") podcastId: Long
    ): Response<PodcastSearchResponse>

    @GET("rss")
    suspend fun getRssFeed(
        @Query("feedUrl") feedUrl: String
    ): Response<String>
}

data class PodcastSearchResponse(
    val resultCount: Int,
    val results: List<PodcastSearchResult>
)

data class PodcastSearchResult(
    val trackId: Long,
    val trackName: String,
    val artistName: String,
    val feedUrl: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val artworkUrl600: String,
    val trackViewUrl: String,
    val genres: List<String>
)