package Model

data class PodcastResult(
    val trackName: String?,
    val artistName: String?,
    val artworkUrl100: String?,
    val feedUrl: String?,
    val primaryGenreName: String? // ✅ This field exists and is named correctly
)
