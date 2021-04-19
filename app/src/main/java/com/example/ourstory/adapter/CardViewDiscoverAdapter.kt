package com.example.ourstory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ourstory.databinding.ItemCardviewBookBinding
import com.example.ourstory.model.Book

class CardViewDiscoverAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<CardViewDiscoverAdapter.CardViewViewHolder>() {
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
        holder.bind(listBook[position])
    }

    override fun getItemCount(): Int = listBook.size


    inner class CardViewViewHolder(private val binding:ItemCardviewBookBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (book: Book){
            with(binding){
                Glide.with(itemView.context)
                    .load(book.image)
                    .apply(RequestOptions().override(350, 550))
                    .into(bookImgId)
                bookTitle.text = book.title
                rating.text = book.rating.toString()

                itemView.setOnClickListener{Toast.makeText(itemView.context, "${book.title}", Toast.LENGTH_SHORT).show()}
            }
        }
    }
}