package com.example.spotifysignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPassword : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        recover_btn.setOnClickListener {
            val email = recover_email_et.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter your Email", Toast.LENGTH_SHORT).show()
            } else {

                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Recovery mail sent to your email",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                            startActivity(Intent(this, Login::class.java))
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Please Check your email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

            }
        }

    }
}