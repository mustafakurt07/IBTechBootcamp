package com.example.ibtechbootcamphmfour.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ibtechbootcamphmfour.R
import com.example.ibtechbootcamphmfour.base.BaseCallBack
import com.example.ibtechbootcamphmfour.model.User
import com.example.ibtechbootcamphmfour.service.ServiceConnector
import com.example.ibtechbootcamphmfour.utils.USER_TOKEN
import com.example.ibtechbootcamphmfour.utils.gone
import com.example.ibtechbootcamphmfour.utils.toast
import com.example.ibtechbootcamphmfour.utils.visible
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private var token : String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        this.window.statusBarColor = resources.getColor(R.color.splash_blue)

        if(isLoggedIn()){

            User.getCurrentInstance().token = token

            ServiceConnector.restInterface.getMe().enqueue(object:BaseCallBack<User>(){

                override fun onSuccess(data: User) {

                    super.onSuccess(data)
                    splashProgressBar.gone()
                    User.getCurrentInstance().setUser(data)

                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    intent.putExtra("state", "home")
                    startActivity(intent)
                    finish()
                }
                override fun onFailure() {
                    super.onFailure()
                    Log.e("error: ", "something went wrong")
                }
            })
        }
        else{
            splashProgressBar.gone()
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.putExtra("state", "login")
            startActivity(intent)
            finish()
        }
    }

    private fun isLoggedIn() : Boolean{
        val token = getToken()
        return token.isNotEmpty()
    }

    private fun getToken() : String{
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        token = sharedPreferences.getString(USER_TOKEN, "")!!
        return token!!
    }
}