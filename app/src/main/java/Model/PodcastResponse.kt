package Model

data class PodcastResponse(
    val resultCount: Int,
    val results: List<Result>
) {
    data class Result(
        val collectionId: Long,
        val collectionName: String,
        val artistName: String,
        val feedUrl: String,
        val artworkUrl100: String,
        val collectionViewUrl: String?,
        val primaryGenreName: String? // <-- make sure it's nullable!
    )
}