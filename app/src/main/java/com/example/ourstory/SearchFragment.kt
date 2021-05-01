package com.example.ourstory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.bluetooth.BluetoothAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ourstory.adapter.CardViewDiscoverAdapter
import com.example.ourstory.model.Book
import com.example.ourstory.model.BookPart
import kotlinx.android.synthetic.main.activity_discover.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}