package com.example.bootcampodev2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class SplashActivity : AppCompatActivity() {
    private val DELAY  :  Long = 3 * 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.krt)

        Timer().schedule(object : TimerTask() {
            override fun run() {

               /* val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)*/
                startActivity<FirstWordTestActivity>{
                    Intent()
                    //After splash activity is shown, other activity passes
                }
                finish()
            }
        }, DELAY)

    }
}