package com.example.ibtechbootcamphmfive.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ibtechbootcamphmfive.adapter.TabLayoutAdapter
import com.example.ibtechbootcamphmfive.databinding.FragmentTabLayoutControllerBinding

class TabLayoutControllerFragment : Fragment() {
    private var _binding: FragmentTabLayoutControllerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabLayoutControllerBinding.inflate(inflater, container, false)
        val view = binding.root


        /**
         * Add TabLayout's tab
         */
        val adapter = TabLayoutAdapter(childFragmentManager)
        adapter.addFragment(MovieFragment(), "Movies")
        adapter.addFragment(FavoriteFragment(), "Favourites")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

