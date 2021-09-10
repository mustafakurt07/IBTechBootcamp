package com.example.bootcampodev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FirstWordTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.startTransaction {
            add(R.id.kelime_baslat_activity_constrait_layout, FirstWordTestFragment())
        } // extension function for fragment push

    }
}