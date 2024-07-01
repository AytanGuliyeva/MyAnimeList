package com.example.myanimelist.ui.mangaList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myanimelist.databinding.ItemAnimeBinding
import com.example.myanimelist.databinding.ItemMangaBinding
import com.example.myanimelist.ui.animeList.adapter.AnimeListAdapter
import com.example.myanimelist.ui.retrofit.model.Anime
import com.example.myanimelist.ui.retrofit.model.Manga

class MangaAdapter : RecyclerView.Adapter<MangaAdapter.MangaViewHolder>() {

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<Manga>() {
        override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtilCallBack)

    fun submitList(mangaList: List<Manga>) {
        differ.submitList(mangaList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        val binding = ItemMangaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MangaViewHolder(binding)    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
    inner class MangaViewHolder(private val binding: ItemMangaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(manga: Manga) {
            binding.textTitle.text=manga.title
            binding.textScore2.text=manga.score.toString()
            val genresText = manga.genres.joinToString(", ") { it.name }
            binding.textGenre2.text = genresText
            binding.textChapters2.text=manga.chapters.toString()
            Glide.with(binding.imageManga.context).load(manga.images.jpg.image_url).into(binding.imageManga)

        }
    }

}