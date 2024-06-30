package com.example.myanimelist.ui.retrofit.model



data class Anime(
    val mal_id: Int,
    val title: String,
    val images: Images,
    val score: Double
)

data class AnimeDetailResponse(
val mal_id: Int,
val rank: Int,
val title: String,
val url: String,
val images: Images,
val type: String,
val episodes: Int?,
val start_date: String?,
val end_date: String?,
val members: Int,
val score: Double
)
data class Images(
    val jpg: Jpg,
)
data class Characters(
    val mal_id: Int,
    val url: String,
    val images: Images,
    val name: String,
    val name_kanji: String,
    val nicknames: List<String>,
    val favorites: Int,
    val about: String
)

data class CharactersResponse(
    val data: List<Characters>
)


data class TopAnimeResponse(
    val data: List<AnimeDetailResponse>
)
data class Jpg(
    val image_url: String
)



