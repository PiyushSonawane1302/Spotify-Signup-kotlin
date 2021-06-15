package com.example.spotifysignup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_home,container,false)

        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date
        val hour = cal[Calendar.HOUR_OF_DAY]

        if (hour > 0)
            view.greet_text.text = "Good Morning !" //After 12 AM

        if (hour > 12)
            view.greet_text?.text = "Good Afternoon !" // After 12pm

        if (hour > 17)
            view.greet_text?.text = "Good Evening !" // After 5pm

        if (hour > 22)
            view.greet_text?.text = "Good Night !" // After 10pm

     return view

    }
}