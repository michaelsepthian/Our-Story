package com.example.ourstory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ourstory.adapter.CardViewDiscoverAdapter
import com.example.ourstory.model.Book
import com.example.ourstory.model.BookPart
import kotlinx.android.synthetic.main.activity_discover.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class DiscoverActivity : AppCompatActivity() {

    private val listBook = ArrayList<Book>()
    private var title = "Discover"
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        setActionBarTitle(title)
        progressBar.visibility = View.VISIBLE
        recyclerview_id.setHasFixedSize(true)
        getListBook()
    }

    private fun setActionBarTitle(title: String?){
        supportActionBar?.title = title
    }

    private fun getListBook(){
        progressBar.visibility = View.VISIBLE
//        val client = AsyncHttpClient()
        var url = "https://api.wattpad.com:443/v4/stories"
        val request = Request.Builder()
            .header("Authorization", "COmAsfoTl5bHFOoHoKl8uQCo12cA8sl2ytzk2RPu3uRB")
            .url(url)
            .build()

        client.newCall(request).enqueue(object :  Callback {
            override fun onFailure(call: Call, e: IOException) {
                progressBar.visibility = View.INVISIBLE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                //creating json object
                val json_contact:JSONObject = JSONObject(str_response)
                //creating json array
                var jsonarray_info:JSONArray = json_contact.getJSONArray("stories")
                var size: Int = jsonarray_info.length()
                val list = ArrayList<Book>()
                for(i in 0 until size) {
                    var json_objectdetail = jsonarray_info.getJSONObject(i)
                    var book: Book = Book()
                    book.id = json_objectdetail.getString("id")
                    book.title = json_objectdetail.getString("title")
                    book.description = json_objectdetail.getString("description")
                    book.image = json_objectdetail.getString("cover")
                    book.rating = json_objectdetail.getInt("rating")

                    var jsonarray_part: JSONArray = json_objectdetail.getJSONArray("parts")
                    var part: BookPart = BookPart()
                    for (j in 0 until jsonarray_part.length()){
                        var json_objectpart = jsonarray_part.getJSONObject(i)
                        part.id = json_objectpart.getInt("id")
                        part.title = json_objectpart.getString("title")
                        part.url = json_objectpart.getString("url")
                        part.rating = json_objectpart.getInt("rating")
                    }
                    book.part.add(part)
                    listBook.add(book)
                }
                runOnUiThread{
                    recyclerview_id.layoutManager = GridLayoutManager(applicationContext,2)
                    val cardViewDiscoverAdapter = CardViewDiscoverAdapter(listBook)
                    recyclerview_id.adapter = cardViewDiscoverAdapter
                }
                progressBar.visibility = View.INVISIBLE
            }
        })
    }
}