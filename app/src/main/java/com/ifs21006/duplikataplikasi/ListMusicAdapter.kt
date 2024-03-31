package com.ifs21006.duplikataplikasi

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21006.duplikataplikasi.databinding.ItemRowMusicBinding

class ListMusicAdapter (private val listDino: ArrayList<Music>) :
    RecyclerView.Adapter<ListMusicAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowMusicBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val dino = listDino[position]
        holder.binding.iAlbum.setImageResource(dino.photo)
        holder.binding.tAlbum.text = dino.name

        }
    override fun getItemCount(): Int = listDino.size

    class ListViewHolder(var binding: ItemRowMusicBinding) :
        RecyclerView.ViewHolder(binding.root)
}