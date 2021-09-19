package com.example.ibtechbootcamphm3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         navController = findNavController(R.id.fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        //bottomNavigationView.gone()


    }


   /* override fun onDestroy() {
        super.onDestroy()
        if (!navController.popBackStack())
        {
            finish()
        }
    }*/

    fun hideNavigation() {
        bottomNavigationView.postDelayed(
            {
                bottomNavigationView.gone()

            },0
        )
    }

    fun showNavigation() {
        bottomNavigationView.postDelayed(
            {
                bottomNavigationView.visible()

            }, 0//3000
        )
    }
   /* fun exit()
    {
        AlertDialog.Builder(this)
            .setTitle("Bilgi")
            .setMessage("Çıkmak istediğinize emin misiniz?")
            .setCancelable(false)
            .setPositiveButton("Tamam"
            ) { dialog, _ -> finish() }
            .show()
    }

   override fun onBackPressed() {
        var backStackEnteryCount=supportFragmentManager.backStackEntryCount
        if(backStackEnteryCount!=0)
        {
           exit()
        }
        else
        super.onBackPressed()

    }*/
}