package com.example.spotifysignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        val firebaseUser:FirebaseUser? = firebaseAuth.currentUser

        if (firebaseUser!=null){
            finish()
            startActivity(Intent(this,SpotifyHome::class.java))
        }

        login_btn.setOnClickListener {
            val email = login_email_et.text.toString().trim()
            val password = login_pass_et.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please Check All fields", Toast.LENGTH_SHORT).show()
            }else{
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                    OnCompleteListener {
                        if (it.isSuccessful){
                            checkMailVerification()
                        }else{
                            Toast.makeText(this, "Account not exist Please create account or Check Details", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))
                        }
                    })

            }


        }

        forgotPass_tv.setOnClickListener {
            val intent = Intent(this,ForgotPassword::class.java)
            startActivity(intent)
        }

    }

    private fun checkMailVerification() {
       val firebaseUser:FirebaseUser? = firebaseAuth.currentUser

        if (firebaseUser != null) {

            if (firebaseUser.isEmailVerified){
                Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(Intent(this,SpotifyHome::class.java))
            }else{
                Toast.makeText(this, "Please Verify your email", Toast.LENGTH_SHORT).show()
                firebaseAuth.signOut()
                finish()
            }

        }
    }

}