package com.example.ibtechbootcamphm3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.ibtechbootcamphm3.R
import com.example.ibtechbootcamphm3.adapter.AvatarFragmentAdapter
import com.example.ibtechbootcamphm3.model.Data
import com.example.ibtechbootcamphm3.navigateToNextFragment
import com.example.ibtechbootcamphm3.statusBarColor
import kotlinx.android.synthetic.main.fragment_profil_avatar.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfilAvatarFragment:BaseFragment() {
    override fun isNavigationbarVisible(): Boolean = false
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val callback=object: OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed() {

               findNavController().navigate(R.id.action_profilAvatarFragment_to_profileFragment)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        return inflater.inflate(R.layout.fragment_profil_avatar, container, false)
    }

    override fun getLayoutID(): Int {
       return R.id.profilAvatarFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // The data, basically
        val dataList = ArrayList<Data>()
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_ONE, getString(R.string.choose_avatar)))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar1))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar2))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar3))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar4))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar5))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar6))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar7))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar8))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar9))
        // added more item
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar1))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar3))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar5))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar7))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar9))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar2))
        dataList.add(Data(AvatarFragmentAdapter.VIEW_TYPE_TWO, null, R.drawable.ic_avatar4))

        //  recyclerview part
        val adapter = AvatarFragmentAdapter(requireContext(), dataList)
        recyclerView = activity?.findViewById(R.id.recyclerview) ?: RecyclerView(requireContext())

        // span count of 3
        val layoutManager = GridLayoutManager(requireContext(), 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            //set span  count for item
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0 -> 3
                    else -> 1
                }
            }
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        // animation off
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false





        /*button.setOnClickListener {
            navigateToNextFragment(R.id.action_profilAvatarFragment_to_profileFragment)
        }*/
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarColor(R.color.battle_background)
    }



}