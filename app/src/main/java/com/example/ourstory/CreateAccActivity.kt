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

class CreateAccActivity : AppCompatActivity() {
    lateinit var mDatabase : DatabaseReference
    val mAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create__acc)

        mDatabase = FirebaseDatabase.getInstance().getReference("USERS")

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
            mAuth.createUserWithEmailAndPassword(emailRegis, passwordRegis).addOnCompleteListener(this,
                OnCompleteListener { task ->
                    if(task.isSuccessful){
                        val user = mAuth.currentUser
                        val uid = user!!.uid
                        val url = "https://image.shutterstock.com/image-vector/chef-cooking-hat-vector-logo-260nw-1264315489.jpg"
                        val userData = User(firstName, lastName, url, null, null)

                        mDatabase.child(uid).setValue(userData)
                        startActivity(Intent(this, LoginActivity::class.java))
                        Toast.makeText(this, "Successfully Registered : )", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(this, "Error registering, " + task.exception?.message , Toast.LENGTH_LONG).show()
                    }
                })
        }else{
            Toast.makeText(this,"Please fill up the Credentials : |", Toast.LENGTH_LONG).show()
        }
    }


}