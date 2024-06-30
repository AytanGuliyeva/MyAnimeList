package com.example.myanimelist.ui.animeList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myanimelist.databinding.ItemAnimeBinding
import com.example.myanimelist.ui.retrofit.model.Anime

class AnimeListAdapter : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<Anime>() {
        override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtilCallBack)

    fun submitList(animeList: List<Anime>) {
        differ.submitList(animeList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            binding.textScore2.text = anime.score.toString()
            binding.textTitle.text = anime.title
            Glide.with(binding.imageAnime.context).load(anime.images.jpg.image_url).into(binding.imageAnime)
        }
    }
}
