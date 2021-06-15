package com.example.spotifysignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseAppLifecycleListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*

class SignUp : AppCompatActivity() {

    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        signup_button.setOnClickListener {
            val email = signup_email_et.text.toString().trim()
            val password = signip_pass_et.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please Check All fields", Toast.LENGTH_SHORT).show()
            } else if (password.length < 8) {
                Toast.makeText(this, "Password Should be 8 characters long", Toast.LENGTH_SHORT)
                    .show()
            } else {

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(applicationContext,"Registration Successful",Toast.LENGTH_SHORT).show()
                            sendEmailVerification()
                        }
                        else{
                            Toast.makeText(applicationContext,"Failed to Register",Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    })

            }
        }

    }

    private fun sendEmailVerification() {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        if (firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener {
                Toast.makeText(applicationContext,"Verification Email Sent on given Email",Toast.LENGTH_SHORT).show()
                firebaseAuth.signOut()
                finish()
                startActivity(Intent(this,Login::class.java))
            }
        }
        else{
            Toast.makeText(applicationContext,"Failed to verify",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}