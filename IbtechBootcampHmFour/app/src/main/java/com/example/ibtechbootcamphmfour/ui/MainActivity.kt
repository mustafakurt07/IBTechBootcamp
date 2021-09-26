package com.example.ibtechbootcamphmfour.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.ibtechbootcamphmfour.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentState = intent.getStringExtra("state")

        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val myGraph = inflater.inflate(R.navigation.nav_graph)

        if(currentState == "home"){
            myGraph.startDestination = R.id.homeFragment
        }
        else if(currentState == "login"){
            myGraph.startDestination = R.id.loginFragment
        }

        navHostFragment.navController.graph = myGraph




    }
}