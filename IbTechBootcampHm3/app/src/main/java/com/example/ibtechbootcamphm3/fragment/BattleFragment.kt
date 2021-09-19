package com.example.ibtechbootcamphm3.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ibtechbootcamphm3.R
import com.example.ibtechbootcamphm3.navigateToNextFragment
import com.example.ibtechbootcamphm3.statusBarColor
import kotlinx.android.synthetic.main.activity_main.*

class BattleFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val callback=object: OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {

                    AlertDialog.Builder(requireContext())
                        .setTitle("Bilgi")
                        .setMessage("Çıkmak istediğinize emin misiniz?")
                        .setCancelable(false)
                        .setNegativeButton("No"){ dialog, _ ->dialog.dismiss()}
                        .setPositiveButton("Yes"
                        ) { dialog, _ -> activity?.finish() }   //if you press back on the battle page it closes the app
                        .show()





            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return inflater.inflate(R.layout.fragment_battle, container, false)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarColor(R.color.battle_background)
    }
    override fun getLayoutID(): Int {
        return R.id.battleFragment
    }

    override fun isNavigationbarVisible(): Boolean = true


}