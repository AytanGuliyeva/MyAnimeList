package com.example.myanimelist.ui.mangaList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myanimelist.R
import com.example.myanimelist.databinding.FragmentCharactersBinding
import com.example.myanimelist.databinding.FragmentMangaListBinding
import com.example.myanimelist.ui.characters.CharactersViewModel
import com.example.myanimelist.ui.characters.adapter.CharactersAdapter


class MangaListFragment : Fragment() {
    private lateinit var binding: FragmentMangaListBinding
    private val viewModel: MangaListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMangaListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchManga()
        viewModel.mangaList.observe(viewLifecycleOwner){ characters ->
            Log.e("TAG", "onViewCreated: $characters", )
        }
    }

}