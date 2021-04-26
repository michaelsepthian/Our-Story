package com.example.ourstory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_main.*

class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //new
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
//        setSupportActionBar(toolbar)
//
//        val toggle = ActionBarDrawerToggle(
//            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        nav_view.setNavigationItemSelectedListener(this)
//
//        displayScreen(-1)

        //new
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> (Toast.makeText(this, "Menu Clicked", Toast.LENGTH_SHORT).show())
            R.id.nav_myfavorites -> (Toast.makeText(this, "favorite Clicked", Toast.LENGTH_SHORT).show())
            R.id.nav_myprofile -> (Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show())
            R.id.nav_signout -> (Toast.makeText(this, "Signout Clicked", Toast.LENGTH_SHORT).show())
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

//    override fun onBackPressed() {
//        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
//            drawer_layout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
//
//    fun displayScreen(id: Int){
//
//        // val fragment =  when (id){
//
//        when (id){
//            R.id.nav_home -> {
//                supportFragmentManager.beginTransaction().replace(R.id DiscoverActivity()).commit()
//            }
//
//            R.id.nav_myprofile -> {
//                supportFragmentManager.beginTransaction().replace(R.id ProfileActivity()).commit()
//            }
//
//            R.id.nav_myfavorites -> {
//                supportFragmentManager.beginTransaction().replace(R.id FavoriteActivity()).commit()
//            }
//
//            R.id.nav_signout -> {
//
//            }
//        }
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        // Handle navigation view item clicks here.
//
//        displayScreen(item.itemId)
//
//        drawer_layout.closeDrawer(GravityCompat.START)
//        return true
//    }

}