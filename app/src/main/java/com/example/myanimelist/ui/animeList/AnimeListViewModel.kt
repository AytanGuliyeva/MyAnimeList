package com.example.myanimelist.ui.animeList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelist.ui.retrofit.Repository
import com.example.myanimelist.ui.retrofit.model.AnimeDetailResponse
import com.example.myanimelist.ui.retrofit.model.AnimeSearchResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class AnimeListViewModel:ViewModel() {
    private val repository = Repository()

    private val _animeResult = MutableLiveData<AnimeSearchResponse>()
    val animeResult: LiveData<AnimeSearchResponse>
        get() = _animeResult

    private val _animeDetailList = MutableLiveData<List<AnimeDetailResponse>>()
    val animeDetailList: LiveData<List<AnimeDetailResponse>>
        get() = _animeDetailList

    fun fetchTopAnimeList() {
        viewModelScope.launch {
            try {
                val response = repository.getTopAnimeList()
                if (response.isSuccessful) {
                    _animeDetailList.postValue(response.body()?.data)
                    Log.e("TAG", "fetchTopAnimeList: ${response.body()}")
                } else {
                    Log.e("TAG", "fetchTopAnimeList: Error ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("TAG", "fetchTopAnimeList: Exception ${e.message}")
            }
        }
    }

    fun fetchAnimeList() {
        viewModelScope.launch {
            val response: Response<AnimeSearchResponse> = repository.getAnimes()
            if (response.isSuccessful) {
                _animeResult.postValue(response.body())
            }
        }
    }
}