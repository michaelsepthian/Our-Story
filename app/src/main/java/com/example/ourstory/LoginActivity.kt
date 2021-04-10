package com.example.ourstory

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login.setOnClickListener {
            loginUser()
        }

        signup_button.setOnClickListener{
            val intent = Intent(this, CreateAccActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginUser() {
        val sharedPreference : SharedPreference = SharedPreference(this)

        var emailLog = email.text.toString()
        var passwordLog = password.text.toString()

        if(emailLog.isEmpty()){
            email.error = "Please input your email"
            email.requestFocus()
            return
        }

        if(passwordLog.isEmpty()){
            password.error = "Please input password"
            password.requestFocus()
            return
        }

        if(!emailLog.isEmpty() && !passwordLog.isEmpty()){
            this.mAuth.signInWithEmailAndPassword(emailLog, passwordLog).addOnCompleteListener(this, OnCompleteListener { task ->

                if(task.isSuccessful){
                    sharedPreference.save("email",emailLog)
                    sharedPreference.save("pwd",passwordLog)
                    cekActivity()
                }else{
                    Toast.makeText(this,"Error Logging in : (", Toast.LENGTH_SHORT).show()
                }
            })
        }else{
            Toast.makeText(this,"Please fill up the Credentials : | ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cekActivity() {
        mDatabase = FirebaseDatabase.getInstance().getReference("Users")
        val user = FirebaseAuth.getInstance().currentUser
        var cek: String? = null

        val uid = user!!.uid

        mDatabase.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p: DataSnapshot) {
                cek = p.child("gender").value.toString()

                if (cek == "null") {
                    startActivity(Intent(this@LoginActivity, FavoriteActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this@LoginActivity, DiscoverActivity::class.java))
                    finish()
                }
            }
        })
    }

    private var doubleBackToExit = false
    override fun onBackPressed() {
        super.onBackPressed()

        if(doubleBackToExit){
            super.onBackPressed()
            System.exit(0)
        } else {
            this.doubleBackToExit = true
            Toast.makeText(this,"Click back again to exit",Toast.LENGTH_SHORT).show()
            Handler().postDelayed(Runnable{doubleBackToExit = false}, 2000)
        }
    }

}