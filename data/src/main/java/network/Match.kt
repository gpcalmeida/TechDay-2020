package network

data class Match(
    val home: String,
    val away: String,
    val score: String,
    val tournament: String,
    val info: Info?
)