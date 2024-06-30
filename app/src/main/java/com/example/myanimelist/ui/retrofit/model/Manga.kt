package com.example.myanimelist.ui.retrofit.model

import com.bumptech.glide.load.ImageHeaderParser

data class Manga(
    val malId: Int,
    val url: String,
    val images: Images,
    val title: String,
    val titleEnglish: String,
    val titleJapanese: String,
    val type: String,
    val chapters: Int,
    val score: Double,
    val synopsis: String,
    val background: String,
    val authors: List<Author>,
    val genres: List<Genre>,
)
data class Author(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
)
data class Genre(
    val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

data class MangaResponse(
    val data: List<Manga>
)