package com.example.myanimelist.ui.charactersAnime

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myanimelist.ui.retrofit.Repository
import com.example.myanimelist.ui.retrofit.model.Characters
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {
    private val repository = Repository()

    private val _charactersList = MutableLiveData<List<Characters>>()
    val charactersList: LiveData<List<Characters>>
        get() = _charactersList

    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val response = repository.getCharacters()
                if (response.isSuccessful) {
                    _charactersList.postValue(response.body()?.data ?: emptyList())
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
