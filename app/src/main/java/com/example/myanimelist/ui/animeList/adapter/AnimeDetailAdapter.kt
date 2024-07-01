package com.example.myanimelist.ui.animeList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myanimelist.databinding.ItemAnimeTopBinding
import com.example.myanimelist.ui.retrofit.model.AnimeDetailResponse

class AnimeDetailAdapter : RecyclerView.Adapter<AnimeDetailAdapter.AnimeDetailViewHolder>() {

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<AnimeDetailResponse>() {
        override fun areItemsTheSame(
            oldItem: AnimeDetailResponse,
            newItem: AnimeDetailResponse
        ): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(
            oldItem: AnimeDetailResponse,
            newItem: AnimeDetailResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtilCallBack)

    fun submitList(animeList: List<AnimeDetailResponse>) {
        differ.submitList(animeList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeDetailViewHolder {
        val binding =
            ItemAnimeTopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeDetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AnimeDetailViewHolder, position: Int) {
        val animeDetail = differ.currentList[position]
        holder.bind(animeDetail)
    }

    inner class AnimeDetailViewHolder(private val binding: ItemAnimeTopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(animeDetail: AnimeDetailResponse) {
            binding.apply {
                textTitle.text = animeDetail.title
                textEpisodes2.text = animeDetail.episodes.toString()
                textScore2.text = animeDetail.score.toString()
                Glide.with(binding.root)
                    .load(animeDetail.images.jpg.image_url)
                    .into(imageAnime)

            }
        }
    }
}
