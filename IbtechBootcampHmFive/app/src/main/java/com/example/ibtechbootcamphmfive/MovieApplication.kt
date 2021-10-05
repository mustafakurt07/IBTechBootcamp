package com.example.ibtechbootcamphmfive

import android.app.Application
import com.example.ibtechbootcamphmfive.service.ServiceConnector

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ServiceConnector.init()
    }
}