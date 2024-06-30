package com.example.myanimelist.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelist.ui.retrofit.Repository
import com.example.myanimelist.ui.retrofit.model.AnimeDetailResponse

import kotlinx.coroutines.launch

class AnimeDetailViewModel : ViewModel() {
    private val repository = Repository()

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
}
