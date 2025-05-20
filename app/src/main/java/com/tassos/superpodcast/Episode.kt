package com.tassos.superpodcast

data class Episode(
    val title: String,
    val description: String,
    val author: String,
    val imageUrl: String,
    val feedUrl: String,
    val audioUrl: String = "",
    val duration: String = "",
    val pubDate: String = "",
    val guid: String = ""
)
