package com.example.ourstory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {
    
    private lateinit var mAuth : FirebaseAuth
    private lateinit var refUsers : DatabaseReference
    private var firebaseUserID: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        mAuth = FirebaseAuth.getInstance()

        button_save.setOnClickListener {
            updateProfile()
        }

    }
    private fun updateProfile(){
        val firstname = edit_firstname.text.toString()
        val lastname = edit_lastname.text.toString()

        firebaseUserID = mAuth.currentUser!!.uid
        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

        val userHashMap = HashMap<String, Any>()
        userHashMap["firstname"] = firstname
        userHashMap["lastname"] = lastname

        refUsers.updateChildren(userHashMap)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val intent = Intent(this@EditProfileActivity, ProfileActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }
                }

    }
}