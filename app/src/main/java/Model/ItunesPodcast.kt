package Model

data class ITunesPodcast(
    val collectionId: Long,
    val collectionName: String,
    val artistName: String,
    val feedUrl: String,
    val artworkUrl100: String,
    val collectionViewUrl: String?,
    val primaryGenreName: String? // Nullable just in case it's missing
)
