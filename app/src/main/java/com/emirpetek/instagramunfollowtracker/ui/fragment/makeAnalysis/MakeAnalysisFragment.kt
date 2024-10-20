package com.emirpetek.instagramunfollowtracker.ui.fragment.makeAnalysis

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.emirpetek.instagramunfollowtracker.R
import com.emirpetek.instagramunfollowtracker.data.DataItem
import com.emirpetek.instagramunfollowtracker.data.RelationshipsFollowingResponse
import com.emirpetek.instagramunfollowtracker.data.roomData.AnalysisData
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowerData
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowingData
import com.emirpetek.instagramunfollowtracker.databinding.FragmentMakeAnalysisBinding
import com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis.analysis.tabLayoutFragments.FollowersFragment
import com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis.analysis.tabLayoutFragments.FollowingFragment
import com.emirpetek.instagramunfollowtracker.ui.fragment.seeAnalysis.analysis.tabLayoutFragments.ViewPager2Adapter
import com.emirpetek.instagramunfollowtracker.ui.viewmodel.MakeAnalysisViewModel
import com.emirpetek.instagramunfollowtracker.util.RandomKey
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MakeAnalysisFragment : Fragment() {


    private val viewModel: MakeAnalysisViewModel by viewModels()
    private lateinit var binding: FragmentMakeAnalysisBinding
    private lateinit var adapter : ViewPager2Adapter
    val requestFileCodeFollowing = 1
    val requestFileCodeFollowers = 2
    companion object {
        var followerDataItemList: List<DataItem>? = null
        var followingDataItemList: RelationshipsFollowingResponse? = null

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMakeAnalysisBinding.inflate(inflater,container,false)

        binding.imageViewMakeAnalysisBackButton.setOnClickListener { findNavController().popBackStack() }
        binding.imageViewSelectFollowingFile.setOnClickListener { openFilePicker(requestFileCodeFollowing) }
        binding.imageViewSelectFollowerFile.setOnClickListener { openFilePicker(requestFileCodeFollowers) }

        binding.buttonMakeAnalysis.setOnClickListener {

            val randomKey = RandomKey().generateRandomKey()
            val followerData = FollowerData(0,System.currentTimeMillis(), followerDataItemList!!,randomKey)
            val followingData = FollowingData(0,System.currentTimeMillis(), followingDataItemList!!.relationships_following,randomKey)

            viewModel.insertFollower(followerData,requireContext())
            viewModel.insertFollowing(followingData,requireContext())
            val filteredData = filterFollowingOnly(followingData.followers,followerData.followers)
            val analysedData = AnalysisData(0,System.currentTimeMillis(),filteredData,randomKey)

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.insertAnalysedData(analysedData,requireContext())
            }

            Toast.makeText(requireContext(),getString(R.string.visit_analysis_page),Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        return binding.root
    }



    fun openFilePicker(requestCode: Int) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/json" // Sadece JSON dosyalarını göstermek için
        }
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == requestFileCodeFollowers && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->

                try {
                    // Seçilen dosyanın URI'sini al
                    val jsonString = readTextFromUri(uri)

                    //Log.e("şkkdsjfjdsfş",jsonString.toString())

                    jsonString?.let {
                        // JSON'u DataItem listesine dönüştür
                        followerDataItemList = parseJsonToDataItemList(it)!!
                        Glide.with(this).load(R.drawable.positive).into(binding.imageViewFollowerSelectedState)
                        //val dataDb = FollowerData(0,System.currentTimeMillis(), followerDataItemList!!)
                        //viewModel.insert(dataDb,requireContext())
                        //Log.e("data size: ", followerDataItemList!!.toString())

                    }
                }catch (e : Exception){
                    Toast.makeText(requireContext(),getString(R.string.be_sure_right_file),Toast.LENGTH_SHORT).show()
                }

            }
        }

        if (requestCode == requestFileCodeFollowing && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->

                try {
                    // Seçilen dosyanın URI'sini al
                    val jsonString = readTextFromUri(uri)

                    //Log.e("şkkdsjfjdsfş",jsonString.toString())


                    jsonString?.let {
                        // JSON'u DataItem listesine dönüştür
                        followingDataItemList = parseJsonToRelationshipsFollowingResponse(it)!!
                        Glide.with(this).load(R.drawable.positive).into(binding.imageViewFollowingSelectedState)

                        //Log.e("data size: ", HomeFragment.followingDataItemList!!.toString())

                    }
                }catch (e : Exception){
                    Toast.makeText(requireContext(),getString(R.string.be_sure_right_file),Toast.LENGTH_SHORT).show()
                }


            }
        }
    }

    fun readTextFromUri(uri: Uri): String? {
        return try {
            requireContext().contentResolver.openInputStream(uri)?.bufferedReader().use { it?.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // JSON'u data class'a dönüştürür
    fun parseJsonToDataItemList(jsonString: String): List<DataItem>? {
        val gson = Gson()
        val type = object : TypeToken<List<DataItem>>() {}.type
        return try {
            gson.fromJson(jsonString, type)
        } catch (e: JsonSyntaxException) {
            Log.e("JSON Parse Error", "Failed to parse JSON: ${e.message}")
            null
        }
    }

    fun parseJsonToRelationshipsFollowingResponse(jsonString: String): RelationshipsFollowingResponse? {
        val gson = Gson()
        return try {
            gson.fromJson(jsonString, RelationshipsFollowingResponse::class.java)
        } catch (e: JsonSyntaxException) {
            Log.e("JSON Parse Error", "Failed to parse JSON: ${e.message}")
            null
        }
    }

    fun filterFollowingOnly(
        followingList: List<DataItem>,
        followerList: List<DataItem>
    ): List<DataItem> {
        // Follower listesinden değerleri bir sete ekleyin, null değerleri göz ardı edin
        val followerSet = followerList
            .flatMap { it.string_list_data ?: emptyList() } // stringListData null değilse ele al
            .map { it.value }
            .toSet()

        // Following listesinde follower setinde bulunmayan değerleri filtreleyin
        return followingList.filter { followingItem ->
            followingItem.string_list_data?.none { stringData -> stringData.value in followerSet } ?: true
        }
    }
}