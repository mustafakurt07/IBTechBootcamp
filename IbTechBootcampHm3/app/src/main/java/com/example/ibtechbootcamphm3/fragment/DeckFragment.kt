package com.example.ibtechbootcamphm3.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.ibtechbootcamphm3.R
import com.example.ibtechbootcamphm3.navigateToNextFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class DeckFragment : BaseFragment() {
    override fun getLayoutID(): Int =  R.layout.fragment_deck
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val callback=object: OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {

                navigateToNextFragment(R.id.battleFragment)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor(R.color.deck_status_bar)
    }


    override fun isNavigationbarVisible(): Boolean = true

}

