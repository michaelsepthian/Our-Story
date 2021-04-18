package com.example.ourstory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ourstory.adapter.CardViewDiscoverAdapter
import com.example.ourstory.model.Discover
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.client.methods.RequestBuilder
import kotlinx.android.synthetic.main.activity_discover.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class DiscoverActivity : AppCompatActivity() {

    private val listDiscover = ArrayList<Discover>()
    private var title = "Discover"
    private val client = OkHttpClient()

    private var progressBar: ProgressBar = findViewById(R.id.progressBar)
    private var recyclerview_id: RecyclerView = findViewById(R.id.recyclerview_id)

//    companion object {
//        private val TAG = DiscoverActivity::class.java.simpleName
//    }

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
            .url(url)
            .build()

        client.newCall(request).enqueue(object :  Callback {
            override fun onFailure(call: Call, e: IOException) {
                progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                //creating json object
                val json_contact:JSONObject = JSONObject(str_response)
                //creating json array
                var jsonarray_info:JSONArray = json_contact.getJSONArray("stories")
                var size: Int = jsonarray_info.length()
                val list = ArrayList<Discover>()
                for(i in 0 until size) {
                    var json_objectdetail = jsonarray_info.getJSONObject(i)
                    var discover: Discover = Discover()
                    discover.id = json_objectdetail.getString("id").toInt()
                    discover.title = json_objectdetail.getString("title")
                    discover.image = json_objectdetail.getString("cover")
                    discover.rating = json_objectdetail.getString("rating").toInt()
                    listDiscover.add(discover)
                }
                runOnUiThread{
                    recyclerview_id.layoutManager = GridLayoutManager(applicationContext,2)
                    val cardViewDiscoverAdapter = CardViewDiscoverAdapter(listDiscover)
                    recyclerview_id.adapter = cardViewDiscoverAdapter
                }
                progressBar.visibility = View.GONE
            }
        })
//        client.get(url, object: AsyncHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
//                // Jika koneksi berhasil
//                progressBar.visibility = View.INVISIBLE
//                // Parsing JSON
//                val listBook = ArrayList<Discover>()
//                val result = String(responseBody)
//                Log.d(TAG, result)
//                try {
//                    val jsonArray = JSONArray(result)
//
//                    for (i in 0 until jsonArray.length()) {
//                        val jsonObject = jsonArray.getJSONObject(i)
//                        val id = jsonObject.getString("id")
//                        val title = jsonObject.getString("title")
//                        val cover = jsonObject.getString("cover")
//                        listBook.add()
//                    }
//
//                    val adapter = ArrayAdapter(this@ListQuotesActivity,  android.R.layout.simple_list_item_1, listQuote)
//                    listQuotes.adapter = adapter
//                } catch (e: Exception) {
//                    Toast.makeText(this@ListQuotesActivity, e.message, Toast.LENGTH_SHORT).show()
//                    e.printStackTrace()
//                }
//            }
//        })
    }
}