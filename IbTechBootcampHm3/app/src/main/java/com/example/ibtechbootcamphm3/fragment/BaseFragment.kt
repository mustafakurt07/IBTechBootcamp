package com.example.ibtechbootcamphm3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.example.ibtechbootcamphm3.MainActivity
import com.example.ibtechbootcamphm3.gone
import com.example.ibtechbootcamphm3.visible
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseFragment : Fragment(), FragmentInterface, LifecycleObserver {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutID(), container, false)

    }

    override fun onResume() {
        super.onResume()

        val baseActivity = activity as MainActivity
        if(isNavigationbarVisible())
            baseActivity.showNavigation()
        else
            baseActivity.hideNavigation()


    }

    abstract fun getLayoutID() : Int
    // Function to change the Status Bar color for fragments page
    fun changeStatusBarColor(id: Int) {
        activity?.window?.statusBarColor = resources.getColor(id)
    }


}