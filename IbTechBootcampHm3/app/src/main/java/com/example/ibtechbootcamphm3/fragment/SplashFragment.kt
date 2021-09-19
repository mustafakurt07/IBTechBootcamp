package com.example.ibtechbootcamphm3.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.fragment.findNavController
import com.example.ibtechbootcamphm3.*
import com.example.ibtechbootcamphm3.const.Const
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment: BaseFragment() {

   /* override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }*/

    override fun getLayoutID(): Int =  R.layout.fragment_splash
    override fun isNavigationbarVisible(): Boolean = false




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_battleFragment)
        }, Const.DELAY)

        /*Handler().postDelayed(Runnable {
            //This method will be executed once the timer is over
            // Start your app home fragment
            progressBar.visible()
            findNavController().navigate(R.id.action_splashFragment_to_battleFragment)

            // navigateToNextFragment(R.id.action_splashFragment_to_homeFragment)
            // close this fragment
        }, Const.DELAY)*/
    //navigateToNextFragment(R.id.action_splashFragment_to_homeFragment)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarColor(R.color.app_background_color)
    }


    //override fun isNavigationbarVisible() = false



}