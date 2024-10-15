package com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirpetek.instagramunfollowtracker.R
import com.emirpetek.instagramunfollowtracker.databinding.FragmentFollowingBinding
import com.emirpetek.instagramunfollowtracker.ui.adapter.FollowersFragmentAdapter
import com.emirpetek.instagramunfollowtracker.ui.viewmodel.MakeAnalysisViewModel


class FollowingFragment : Fragment() {


    private lateinit var binding: FragmentFollowingBinding
    private lateinit var adapter: FollowersFragmentAdapter
    private val viewModel: MakeAnalysisViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater,container,false)

        viewModel.getFollowers(requireContext())
        viewModel.getFollowersList().observe(viewLifecycleOwner, Observer { list ->
            for (l in list){
                binding.recyclerViewFollowingUsers.setHasFixedSize(true)
                adapter = FollowersFragmentAdapter(requireContext(), l.followers)
                binding.recyclerViewFollowingUsers.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recyclerViewFollowingUsers.adapter = adapter
            }

        })


        return binding.root
    }

}