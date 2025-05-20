package com.tassos.superpodcast

data class PodCast(
    val title: String,
    val feedUrl: String,
    val imageUrl: String,
    val description: String = "",
    val author: String = "",
    val category: String = "",
    val language: String = "",
    val lastBuildDate: String = ""
)