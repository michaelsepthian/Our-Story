package com.example.ourstory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourstory.R
import com.example.ourstory.adapter.ListPartAdapter
import com.example.ourstory.model.Book
import com.example.ourstory.model.BookPart
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookFragment : Fragment() {

    companion object{
        var title_book = ""
        var image = ""
        var numPart = 0
        var description = ""
        var penulis = ""
        var bookPart = ArrayList<BookPart>()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favorite.setOnClickListener {
            favorite_btn()
        }

        share.setOnClickListener{
            share_btn()
        }

//        setActionBarTitle(title_book)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Picasso.get().load(BookActivity.image).into(img_book)

        tv_title.text = BookActivity.title_book
        tv_part.text = BookActivity.numPart.toString()
        tv_description.text = BookActivity.description
        tv_penulis.text = BookActivity.penulis
        part.layoutManager = LinearLayoutManager(context)
        val listPartAdapter = ListPartAdapter(BookActivity.bookPart)
        part.adapter = listPartAdapter
    }

    private fun favorite_btn() {
//        val favoriteIntent = googleSignInClient.signInIntent
//        startActivityForResult(favoriteIntent, LoginActivity.RC_SIGN_IN)
        Toast.makeText(context,"favorite!!!", Toast.LENGTH_SHORT).show()
    }

    private fun share_btn() {
        Toast.makeText(context,"SHARE!!!", Toast.LENGTH_SHORT).show()
    }


}