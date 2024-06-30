package com.example.myanimelist.ui.retrofit

import android.util.Log
import com.example.myanimelist.ui.retrofit.model.AnimeDetailResponse
import com.example.myanimelist.ui.retrofit.model.AnimeSearchResponse
import com.example.myanimelist.ui.retrofit.model.Characters
import com.example.myanimelist.ui.retrofit.model.CharactersResponse
import com.example.myanimelist.ui.retrofit.model.MangaResponse
import com.example.myanimelist.ui.retrofit.model.TopAnimeResponse
import retrofit2.Response
import kotlin.math.log

class Repository {
    private val jikanApi = RetrofitInstance.getInstance().create(JikanService::class.java)

    suspend fun getAnimes(): Response<AnimeSearchResponse> {
        return jikanApi.getAnimeList()
    }


    suspend fun getTopAnimeList(): Response<TopAnimeResponse> {
        val response = jikanApi.getTopAnime()
        return response
    }
    suspend fun getCharacters(): Response<CharactersResponse> {
        val response = jikanApi.getCharacters()
        return response
    }
    suspend fun getManga(): Response<MangaResponse> {
        val response = jikanApi.getManga()
        return response
    }

}