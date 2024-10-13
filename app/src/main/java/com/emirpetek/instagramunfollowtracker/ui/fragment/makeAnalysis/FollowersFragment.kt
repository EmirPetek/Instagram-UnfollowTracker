package com.emirpetek.instagramunfollowtracker.ui.fragment.makeAnalysis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirpetek.instagramunfollowtracker.R
import com.emirpetek.instagramunfollowtracker.databinding.FragmentFollowersBinding
import com.emirpetek.instagramunfollowtracker.ui.adapter.FollowersFragmentAdapter


class FollowersFragment : Fragment() {


    private lateinit var binding: FragmentFollowersBinding
    private lateinit var adapter:FollowersFragmentAdapter
    private val viewModel: MakeAnalysisViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowersBinding.inflate(inflater,container,false)

        viewModel.getFollowers(requireContext())
        viewModel.getFollowersList().observe(viewLifecycleOwner, Observer { list ->
            for (l in list){
                binding.recyclerViewFollowers.setHasFixedSize(true)
                adapter = FollowersFragmentAdapter(requireContext(),l.followers)
                binding.recyclerViewFollowers.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                binding.recyclerViewFollowers.adapter = adapter
            }

        })


        // Inflate the layout for this fragment
        return binding.root
    }

}