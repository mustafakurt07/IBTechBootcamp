package com.example.ibtechbootcamphmfour

import android.app.Application
import com.example.ibtechbootcamphmfour.service.ServiceConnector


class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        ServiceConnector.init()
    }
}