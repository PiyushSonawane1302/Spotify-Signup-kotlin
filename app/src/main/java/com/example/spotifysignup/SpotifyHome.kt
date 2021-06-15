package com.example.spotifysignup

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_spotify_home.*
import java.sql.Date
import java.util.*

class SpotifyHome : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spotify_home)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        firebaseAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.attributes.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        bottom_nav.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_Container, HomeFragment())
            .commit()
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {

        var selectedFragment: Fragment? =null

        when (it.itemId) {

            R.id.home_menu -> {
               selectedFragment =  HomeFragment()
            }

            R.id.home_search ->{
                selectedFragment =  SearchFragment()
            }

            R.id.home_spotify ->{
                selectedFragment =   SpotifyFragment()
            }

            R.id.home_profile->{
                selectedFragment =    ProfileFragment()
            }
        }
        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_Container,selectedFragment).commit()
        }
        true
    }

}