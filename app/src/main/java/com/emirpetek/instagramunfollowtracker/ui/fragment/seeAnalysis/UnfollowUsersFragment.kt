package com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirpetek.instagramunfollowtracker.R
import com.emirpetek.instagramunfollowtracker.databinding.FragmentUnfollowUsersBinding
import com.emirpetek.instagramunfollowtracker.ui.adapter.FollowersFragmentAdapter

class UnfollowUsersFragment : Fragment() {


    private val viewModel: UnfollowUsersViewModel by viewModels()
    private lateinit var binding: FragmentUnfollowUsersBinding
    private lateinit var adapter: FollowersFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnfollowUsersBinding.inflate(inflater,container,false)

        viewModel.getUnfUsersDB(requireContext())
        viewModel.getUnfUsers().observe(viewLifecycleOwner, Observer { list ->
            for (l in list){
                binding.recyclerViewUnfUsers.setHasFixedSize(true)
                adapter = FollowersFragmentAdapter(requireContext(), l.analysedDatas)
                binding.recyclerViewUnfUsers.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.recyclerViewUnfUsers.adapter = adapter
            }

        })


        return binding.root
    }
}