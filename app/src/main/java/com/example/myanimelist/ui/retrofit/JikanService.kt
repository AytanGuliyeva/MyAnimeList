package com.example.myanimelist.ui.retrofit

import com.example.myanimelist.ui.retrofit.model.AnimeDetailResponse
import com.example.myanimelist.ui.retrofit.model.AnimeSearchResponse
import com.example.myanimelist.ui.retrofit.model.Characters
import com.example.myanimelist.ui.retrofit.model.CharactersResponse
import com.example.myanimelist.ui.retrofit.model.MangaResponse
import com.example.myanimelist.ui.retrofit.model.TopAnimeResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanService {
    @GET("anime")
    suspend fun getAnimeList(): Response<AnimeSearchResponse>

    @GET("top/anime")
    suspend fun getTopAnime(): Response<TopAnimeResponse>

    @GET("characters")
    suspend fun getCharacters(): Response<CharactersResponse>

    @GET("manga")
    suspend fun getManga(): Response<MangaResponse>
}
