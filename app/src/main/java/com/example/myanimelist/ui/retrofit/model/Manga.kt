package com.example.myanimelist.ui.retrofit.model

data class Manga(
    val mal_id: Int,
    val url: String,
    val images: Images,
    val approved: Boolean,
    val titles: List<Title>,
    val title: String,
    val title_english: String?,
    val title_japanese: String,
    val title_synonyms: List<String>,
    val type: String,
    val chapters: Int,
    val volumes: Int,
    val status: String,
    val publishing: Boolean,
    val published: Published,
    val score: Double,
    val scored_by: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val synopsis: String,
    val background: String?,
    val authors: List<Author>,
    val serializations: List<Serialization>,
    val genres: List<Genre>,
    val explicit_genres: List<String>,
    val themes: List<Theme>,
    val demographics: List<Demographic>
)

data class ImageDetails(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)

data class Title(
    val type: String,
    val title: String
)

data class Published(
    val from: String,
    val to: String,
    val prop: Prop,
    val string: String
)

data class Prop(
    val from: DateDetails,
    val to: DateDetails
)

data class DateDetails(
    val day: Int,
    val month: Int,
    val year: Int
)

data class Author(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Serialization(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Genre(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Theme(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Demographic(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class MangaResponse(
    val data: List<Manga>
)