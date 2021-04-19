package com.example.ourstory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourstory.adapter.ListPartAdapter
import com.example.ourstory.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_BOOK = "extra_book"
//        var title_book = ""
//        var image = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

//        val book = intent.extras?.get(EXTRA_BOOK) as Book
        val book = intent.getParcelableExtra(EXTRA_BOOK) as? Book

        if (book != null) {
            setActionBarTitle(book.title)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            Picasso.get()
                .load(book.image)
                .into(img_book)

            tv_title.text = book.title
            tv_part.text = book.numPart.toString()
            tv_description.text = book.description
            part.layoutManager = LinearLayoutManager(this)
            val listPartAdapter = ListPartAdapter(book.part)
            part.adapter = listPartAdapter
        }
//
//        tv_title.text = book.title
//        tv_part.text = book.numPart.toString()
//        tv_description.text = book.description
//        part.layoutManager = LinearLayoutManager(this)
//        val listPartAdapter = ListPartAdapter(book.part)
//        part.adapter = listPartAdapter
    }

    private fun setActionBarTitle(title: String?){
        supportActionBar?.title = title
    }
}