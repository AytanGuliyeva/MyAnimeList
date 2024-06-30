package com.example.myanimelist.ui.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myanimelist.R
import com.example.myanimelist.databinding.FragmentCharactersBinding
import com.example.myanimelist.ui.characters.adapter.CharactersAdapter
import com.example.myanimelist.ui.retrofit.model.Characters

class CharactersFragment : Fragment() {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var charactersAdapter: CharactersAdapter
    private var charactersListFull: List<Characters> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charactersAdapter = CharactersAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charactersAdapter
        }
        viewModel.fetchCharacters()
        viewModel.charactersList.observe(viewLifecycleOwner, Observer { characters ->
            charactersListFull = characters
            charactersAdapter.submitList(characters)
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterCharacters(newText)
                return true
            }
        })
    }

    private fun filterCharacters(query: String?) {
        val filteredList = mutableListOf<Characters>()

        if (!query.isNullOrEmpty()) {
            charactersListFull.forEach { character ->
                if (character.name.contains(query, ignoreCase = true)) {
                    filteredList.add(character)
                }
            }
        } else {
            filteredList.addAll(charactersListFull)
        }

        charactersAdapter.submitList(filteredList)
        binding.recyclerView.scrollToPosition(0)

    }
}
