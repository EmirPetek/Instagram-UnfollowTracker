package com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.emirpetek.instagramunfollowtracker.databinding.FragmentSeeAnalysisListBinding
import com.emirpetek.instagramunfollowtracker.ui.adapter.SeeAnalysisListFragmentAdapter
import com.emirpetek.instagramunfollowtracker.ui.viewmodel.SeeAnalysisListViewModel


class SeeAnalysisListFragment : Fragment() {

    private lateinit var binding: FragmentSeeAnalysisListBinding
    private val viewmodel: SeeAnalysisListViewModel by viewModels()
    private lateinit var adapter: SeeAnalysisListFragmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeeAnalysisListBinding.inflate(inflater,container,false)


        viewmodel.getAnalysis(requireContext())
        viewmodel.getData().observe(viewLifecycleOwner, Observer { list ->
            binding.recyclerViewSeeAnalysisList.setHasFixedSize(true)
            binding.recyclerViewSeeAnalysisList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = SeeAnalysisListFragmentAdapter(requireContext(),list)
            binding.recyclerViewSeeAnalysisList.adapter = adapter
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}