package com.example.ourstory.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ourstory.BookActivity
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

                val itemBook = Book(
                        book.id,
                        book.title,
                        book.image,
                        book.description,
                        book.numPart,
                        book.rating,
//                        book.part
                )

                itemView.setOnClickListener{
//                    BookActivity.title_book = book.title.toString()
//                    BookActivity.image = book.image.toString()
//                    BookActivity.numPart = book.numPart
//                    BookActivity.description = book.description.toString()
//                    BookActivity.bookPart = book.part
                    val activity = itemView.context as Activity
                    val bookActivity = Intent(activity, BookActivity::class.java)
                    bookActivity.putExtra(BookActivity.EXTRA_BOOK,itemBook)
                    itemView.context.startActivity(bookActivity)
                }
            }
        }
    }
}