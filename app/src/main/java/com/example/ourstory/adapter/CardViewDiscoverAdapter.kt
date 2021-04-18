package com.example.ourstory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ourstory.databinding.ItemCardviewBookBinding
import com.example.ourstory.model.Discover

class CardViewDiscoverAdapter(private val listDiscover: ArrayList<Discover>) : RecyclerView.Adapter<CardViewDiscoverAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): CardViewDiscoverAdapter.CardViewViewHolder {
        val binding = ItemCardviewBookBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return CardViewViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CardViewDiscoverAdapter.CardViewViewHolder,
        position: Int
    ) {
        holder.bind(listDiscover[position])
    }

    override fun getItemCount(): Int = listDiscover.size


    inner class CardViewViewHolder(private val binding:ItemCardviewBookBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (discover: Discover){
            with(binding){
                Glide.with(itemView.context)
                    .load(discover.image)
                    .apply(RequestOptions().override(350, 550))
                    .into(bookImgId)
                bookTitle.text = discover.title
                rating.text = discover.rating.toString()

                itemView.setOnClickListener{Toast.makeText(itemView.context, "${discover.title}", Toast.LENGTH_SHORT).show()}
            }
        }
    }
}