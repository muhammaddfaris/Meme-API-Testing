package com.dapoi.memeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.dapoi.memeapp.data.DataItem
import com.dapoi.memeapp.databinding.ItemListMemeBinding

// sebagai jembatan untuk bikin list ke tampilan
class MemeAdapter : RecyclerView.Adapter<MemeAdapter.ViewHolder>() {

    // variabel buat bikin list
    private var listMeme = ArrayList<DataItem>()

    // ini nanti dipanggil di activity, klo ga percaya ctrl + click di nama fungsinya
    fun setData(items: List<DataItem>) {
        listMeme.clear()
        listMeme.addAll(items)
        notifyDataSetChanged()
    }

    // fungsi buat masukkin data dari end point jadi list
    inner class ViewHolder(
        private val binding: ItemListMemeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindDataMeme(meme: DataItem) {

            // manggil gambar dari end point ke list
            Glide.with(itemView.context)
                .asBitmap()
                .load(meme.url)
                .transition(BitmapTransitionOptions.withCrossFade())
                .into(binding.imgMeme)

            // manggil sumber dari end point ke list
            binding.tvMemeTitle.text = meme.source
        }
    }

    /**
     *
     * menghubungkan item layout dengan adapter
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListMemeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // ngebungkus datanya yang udah dibuat di atas
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataMeme(listMeme[position])
    }

    // menyesuaikan ukuran datanya
    override fun getItemCount(): Int {
        return listMeme.size
    }

}