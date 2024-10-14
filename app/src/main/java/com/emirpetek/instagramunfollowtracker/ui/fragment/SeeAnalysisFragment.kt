package com.emirpetek.instagramunfollowtracker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirpetek.instagramunfollowtracker.R
import com.emirpetek.instagramunfollowtracker.databinding.FragmentSeeAnalysisBinding


class SeeAnalysisFragment : Fragment() {

    private lateinit var binding: FragmentSeeAnalysisBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeeAnalysisBinding.inflate(inflater,container,false)

        binding.recyclerViewCardSeeAnalysis.setHasFixedSize(true)



        // Inflate the layout for this fragment
        return binding.root
    }

}