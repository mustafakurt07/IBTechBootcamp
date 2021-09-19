package com.example.ibtechbootcamphm3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ibtechbootcamphm3.R
import com.example.ibtechbootcamphm3.navigateToNextFragment
import com.example.ibtechbootcamphm3.statusBarColor
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment :BaseFragment() {
    override fun isNavigationbarVisible(): Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val callback=object: OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {

                navigateToNextFragment(R.id.deckFragment)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun getLayoutID(): Int {
        return R.id.profileFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarColor(R.color.battle_background)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /*avatar_button.setOnClickListener {
            navigateToNextFragment(R.id.action_profileFragment_to_profilAvatarFragment)
        }*/
        edit_btn.setOnClickListener {
            navigateToNextFragment(R.id.action_profileFragment_to_profilAvatarFragment)
        }
    }


}