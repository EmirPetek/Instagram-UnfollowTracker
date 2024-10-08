package com.emirpetek.instagramunfollowtracker.ui.fragment.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emirpetek.instagramunfollowtracker.data.DataItem
import com.emirpetek.instagramunfollowtracker.data.RelationshipsFollowingResponse
import com.emirpetek.instagramunfollowtracker.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val requestFileCodeFollowing = 1
    val requestFileCodeFollowers = 2
    var requestCodeForActivityResult = 0
    lateinit var sharedPreferences : SharedPreferences
    lateinit var editor: Editor

    companion object {
        var followerDataItemList: List<DataItem>? = null
        var followingDataItemList: RelationshipsFollowingResponse? = null

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        sharedPreferences = requireContext().getSharedPreferences("selected_list", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        binding.button.setOnClickListener { openFilePicker(requestFileCodeFollowing) }
        binding.button2.setOnClickListener { openFilePicker(requestFileCodeFollowers) }
        binding.button3.setOnClickListener {
            Log.e("sizeler: ", "follower: ${followerDataItemList?.size}  following: ${followingDataItemList?.relationships_following?.size}")
            val followerFilter = followerDataItemList!!
            val followingFilter = followingDataItemList!!.relationships_following


            val result = filterFollowingOnly(followingFilter,followerFilter)
            Log.e("result: ", result.toString())
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    fun openFilePicker(requestCode: Int) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/json" // Sadece JSON dosyalarını göstermek için
        }
        requestCodeForActivityResult = requestCode
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == requestFileCodeFollowers && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                // Seçilen dosyanın URI'sini al
                val jsonString = readTextFromUri(uri)

                //Log.e("şkkdsjfjdsfş",jsonString.toString())

                jsonString?.let {
                    // JSON'u DataItem listesine dönüştür
                    followerDataItemList = parseJsonToDataItemList(it)!!

                    Log.e("data size: ", followerDataItemList!!.toString())

                }
            }
        }

        if (requestCode == requestFileCodeFollowing && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                // Seçilen dosyanın URI'sini al
                val jsonString = readTextFromUri(uri)

                //Log.e("şkkdsjfjdsfş",jsonString.toString())


                jsonString?.let {
                    // JSON'u DataItem listesine dönüştür
                    followingDataItemList = parseJsonToRelationshipsFollowingResponse(it)!!

                    Log.e("data size: ", followingDataItemList!!.toString())

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