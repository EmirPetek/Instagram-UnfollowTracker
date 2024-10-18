package com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis.analysis

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirpetek.instagramunfollowtracker.R
import com.emirpetek.instagramunfollowtracker.databinding.FragmentSeeAnalysisBinding
import com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis.analysis.tabLayoutFragments.FollowersFragment
import com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis.analysis.tabLayoutFragments.FollowingFragment
import com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis.analysis.tabLayoutFragments.ViewPager2Adapter
import com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis.analysis.tabLayoutFragments.UnfollowUsersFragment
import com.emirpetek.instagramunfollowtracker.ui.viewmodel.SeeAnalysisViewModel
import com.google.android.material.tabs.TabLayoutMediator

class SeeAnalysisFragment : Fragment() {

    private val viewModel: SeeAnalysisViewModel by viewModels()
    private lateinit var binding: FragmentSeeAnalysisBinding
    private lateinit var adapter : ViewPager2Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAnalysisBinding.inflate(inflater,container,false)


        initViewPagerAdapter()

        return binding.root
    }

    private fun initViewPagerAdapter(){

        val saveKey = arguments?.getString("saveKey")!!

        val fragmentList = arrayListOf(
            UnfollowUsersFragment(saveKey), FollowersFragment(saveKey), FollowingFragment(saveKey)
        )

        val tabTitles = arrayListOf(getString(R.string.unf_users),getString(R.string.follower_users),getString(R.string.following_users))

        // Adapter init
        val viewPager = binding.viewPager2SeeAnalysis
        adapter = ViewPager2Adapter(childFragmentManager, viewLifecycleOwner.lifecycle,fragmentList)
        viewPager.adapter = adapter

        // TabLayout ile ViewPager bağlantısı yapılır.
        TabLayoutMediator(binding.tabLayoutSeeAnalysis, viewPager) { tab, position ->
            // TabLayout içerisindeki TabItem'lara text atanma işlemi yapılır.
            tab.text = tabTitles[position]
        }.attach()
    }
}