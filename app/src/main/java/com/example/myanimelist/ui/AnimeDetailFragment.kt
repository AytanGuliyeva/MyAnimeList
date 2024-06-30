package com.example.myanimelist.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myanimelist.databinding.FragmentAnimeDetailBinding

class AnimeDetailFragment : Fragment() {
    private lateinit var binding: FragmentAnimeDetailBinding
    private val viewModel: AnimeDetailViewModel by viewModels()
    private lateinit var adapter: AnimeDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AnimeDetailAdapter()
        binding.recyclerViewAnimeDetail.adapter = adapter
        binding.recyclerViewAnimeDetail.layoutManager = LinearLayoutManager(requireContext())
        viewModel.animeDetailList.observe(viewLifecycleOwner, Observer { animeDetailResponse ->
            animeDetailResponse?.let {
                Log.e("TAG", "fetchTopAnimeList2: $animeDetailResponse")
                adapter.submitList(it)
            }
        })

        viewModel.fetchTopAnimeList()
    }

}