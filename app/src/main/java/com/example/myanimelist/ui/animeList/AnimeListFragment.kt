package com.example.myanimelist.ui.animeList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myanimelist.databinding.FragmentAnimeListBinding
import com.example.myanimelist.ui.animeList.adapter.AnimeDetailAdapter
import com.example.myanimelist.ui.animeList.adapter.AnimeListAdapter

class AnimeListFragment : Fragment() {

    private lateinit var binding: FragmentAnimeListBinding
    private val viewModel: AnimeListViewModel by viewModels()
    private lateinit var adapter: AnimeListAdapter
    private lateinit var adapterDetail: AnimeDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AnimeListAdapter()
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())

        adapterDetail = AnimeDetailAdapter()
        binding.rvTopList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopList.adapter = adapterDetail

        viewModel.animeDetailList.observe(viewLifecycleOwner, Observer { animeDetailResponse ->
            animeDetailResponse?.let {
                adapterDetail.submitList(it)
            }
        })

        viewModel.fetchTopAnimeList()
        viewModel.animeResult.observe(viewLifecycleOwner, Observer { response ->
            response?.data?.let {
                adapter.submitList(it)
            }
        })
        viewModel.fetchAnimeList()
    }
}
