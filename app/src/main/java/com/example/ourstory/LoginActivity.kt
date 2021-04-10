package com.example.ourstory

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_create__acc.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email
import kotlinx.android.synthetic.main.activity_login.password

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        button_login.setOnClickListener {
            loginUser()
        }

        signup_button.setOnClickListener {
            val intent = Intent(this, CreateAccActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginUser() {
        val emailLog = email.text.toString()
        val passwordLog = password.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter valid email"
            email.requestFocus()
            return
        }

        if (passwordLog.isEmpty()) {
            password.error = "Please enter password"
            password.requestFocus()
            return
        }

        if(!emailLog.isEmpty() && !passwordLog.isEmpty()){
            mAuth.signInWithEmailAndPassword(emailLog, passwordLog)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val intent = Intent(this@LoginActivity, DiscoverActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this@LoginActivity, "Error Message: " + task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}