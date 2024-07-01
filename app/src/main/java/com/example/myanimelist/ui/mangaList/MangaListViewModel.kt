package com.example.myanimelist.ui.mangaList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelist.ui.retrofit.Repository
import com.example.myanimelist.ui.retrofit.model.Manga
import kotlinx.coroutines.launch

class MangaListViewModel : ViewModel() {
    private val repository = Repository()

    private val _mangaList = MutableLiveData<List<Manga>>()
    val mangaList: LiveData<List<Manga>>
        get() = _mangaList

    fun fetchManga() {
        viewModelScope.launch {
            try {
                val response = repository.getManga()
                if (response.isSuccessful) {
                    _mangaList.postValue(response.body()?.data ?: emptyList())
                    Log.d("CharactersViewModel", "Characters fetched: ${response.body()}")
                } else {
                    Log.e("CharactersViewModel", "Failed to fetch characters: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("CharactersViewModel", "Exception occurred: ${e.message}", e)
            }
        }
    }
}