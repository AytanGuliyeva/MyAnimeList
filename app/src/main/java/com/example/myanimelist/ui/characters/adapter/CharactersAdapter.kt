package com.example.myanimelist.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myanimelist.databinding.ItemAnimeDetailBinding
import com.example.myanimelist.databinding.ItemCharactersBinding
import com.example.myanimelist.ui.retrofit.model.AnimeDetailResponse
import com.example.myanimelist.ui.retrofit.model.Characters
import com.example.myanimelist.ui.retrofit.model.CharactersResponse

class CharactersAdapter:RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<Characters>() {
        override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem.mal_id == newItem.mal_id
        }

        override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffUtilCallBack)

    fun submitList(characters: List<Characters>) {
        differ.submitList(characters)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding = ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(binding)    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val characters = differ.currentList[position]
        holder.bind(characters)
    }
    inner class CharactersViewHolder(private val binding: ItemCharactersBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(characters:Characters){
            binding.textName.text=characters.name
            binding.textAbout2.text=characters.about
            Glide.with(binding.root)
                .load(characters.images.jpg.image_url)
                .into(binding.imageCharacter)
            binding.textKanji2.text=characters.name_kanji
            binding.textNickname2.text=characters.nicknames.toString()

        }
    }
}