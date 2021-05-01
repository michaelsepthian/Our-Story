package com.example.ourstory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourstory.adapter.ListPartAdapter
import com.example.ourstory.model.Book
import com.example.ourstory.model.BookPart
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.activity_login.*

class BookActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_BOOK = "extra_book"
        var title_book = ""
        var image = ""
        var numPart = 0
        var description = ""
        var penulis = ""
        var bookPart = ArrayList<BookPart>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

//        val book = intent.extras?.get(EXTRA_BOOK) as Book
//        val book = intent.getParcelableExtra(EXTRA_BOOK) as? Book

//        if (book != null) {
//            setActionBarTitle(book.title)
//            supportActionBar?.setDisplayShowHomeEnabled(true)
//            Picasso.get()
//                .load(book.image)
//                .into(img_book)
//
//            tv_title.text = book.title
//            tv_part.text = book.numPart.toString()
//            tv_description.text = book.description
//            part.layoutManager = LinearLayoutManager(this)
//            val listPartAdapter = ListPartAdapter(book.part)
//            part.adapter = listPartAdapter
//        }
        favorite.setOnClickListener {
            favorite_btn()
        }


        val actionBar = supportActionBar
        actionBar!!.title = title_book
        actionBar.setDisplayHomeAsUpEnabled(true)

//        setActionBarTitle(title_book)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Picasso.get().load(image).into(img_book)

        tv_title.text = title_book
        tv_part.text = numPart.toString()
        tv_description.text = description
        tv_penulis.text = penulis
        part.layoutManager = LinearLayoutManager(this)
        val listPartAdapter = ListPartAdapter(bookPart)
        part.adapter = listPartAdapter
    }

    private fun favorite_btn() {
        val favoriteIntent = googleSignInClient.signInIntent
        startActivityForResult(favoriteIntent, LoginActivity.RC_SIGN_IN)
    }

    private fun setActionBarTitle(title: String?){
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}