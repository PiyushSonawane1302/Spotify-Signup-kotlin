package com.example.spotifysignup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile,container,false)

        firebaseAuth = FirebaseAuth.getInstance()

        view.signOut_btn.setOnClickListener {
            firebaseAuth.signOut()
            activity?.finish()
            startActivity(Intent(activity,MainActivity::class.java))
        }

        return view
    }

}