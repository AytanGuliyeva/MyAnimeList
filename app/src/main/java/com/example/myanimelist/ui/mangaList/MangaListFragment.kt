package com.example.myanimelist.ui.mangaList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myanimelist.databinding.FragmentMangaListBinding
import com.example.myanimelist.ui.mangaList.adapter.MangaAdapter


class MangaListFragment : Fragment() {
    private lateinit var binding: FragmentMangaListBinding
    private val viewModel: MangaListViewModel by viewModels()
    private lateinit var adapter: MangaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMangaListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MangaAdapter()
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.fetchManga()
        viewModel.mangaList.observe(viewLifecycleOwner) { characters ->
            characters?.let {
                adapter.submitList(it)
            }
        }
    }

}