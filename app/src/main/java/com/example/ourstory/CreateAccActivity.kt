package com.example.ourstory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.example.ourstory.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create__acc.*
import kotlin.collections.HashMap

class CreateAccActivity : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    private lateinit var refUsers : DatabaseReference
    private var firebaseUserID: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create__acc)

        mAuth = FirebaseAuth.getInstance()

        register_button.setOnClickListener {
            registerUser()
        }

        login_button.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun registerUser() {
        val firstName = first_name.text.toString()
        val lastName = last_name.text.toString()
        val emailRegis = email.text.toString()
        val passwordRegis = password.text.toString()
        val retype = confirm_password.text.toString()

        if(firstName.isEmpty()){
            first_name.error = "Please enter name"
            first_name.requestFocus()
            return
        }

        if(lastName.isEmpty()){
            last_name.error = "Please enter name"
            last_name.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            email.error = "Please enter valid email"
            email.requestFocus()
            return

        }

        if(passwordRegis.isEmpty()){
            password.error = "Please enter password"
            password.requestFocus()
            return

        }

        if(retype.isEmpty() || passwordRegis != retype){
            confirm_password.error = "Incorrect re-type password"
            confirm_password.requestFocus()
            return

        }

        if(!emailRegis.isEmpty() && !passwordRegis.isEmpty() && !firstName.isEmpty() && !retype.isEmpty() && !lastName.isEmpty()){
            mAuth.createUserWithEmailAndPassword(emailRegis,passwordRegis)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        firebaseUserID = mAuth.currentUser!!.uid
                        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserID
                        userHashMap["firstname"] = firstName
                        userHashMap["lastname"] = lastName
                        userHashMap["profile"] = "https://firebasestorage.googleapis.com/v0/b/our-story-13e4c.appspot.com/o/profile.png?alt=media&token=d0af2b17-0694-470e-a6b0-cb14ef54c2b7"
                        userHashMap["cover"] = "https://firebasestorage.googleapis.com/v0/b/our-story-13e4c.appspot.com/o/cover.jpg?alt=media&token=e72663f1-68bb-4f41-b214-e0eb547d3dc2"
                        userHashMap["status"] = "offline"
                        userHashMap["search"] = firstName.toLowerCase()

                        refUsers.updateChildren(userHashMap)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    val intent = Intent(this@CreateAccActivity, DiscoverActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                    }
                    else{
                        Toast.makeText(this@CreateAccActivity, "Error Message: " + task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                    }
                }
        }else{
            Toast.makeText(this,"Please fill up the Credentials : |", Toast.LENGTH_LONG).show()
        }
    }


}