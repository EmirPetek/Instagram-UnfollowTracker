package com.emirpetek.instagramunfollowtracker.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirpetek.instagramunfollowtracker.data.roomData.AnalysisData
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowerData
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowingData
import com.emirpetek.instagramunfollowtracker.repo.database.AnalysisDB
import com.emirpetek.instagramunfollowtracker.repo.database.FollowersDB
import com.emirpetek.instagramunfollowtracker.repo.database.FollowingDB

class MakeAnalysisViewModel : ViewModel() {
    private val followersRepo = FollowersDB
    private val followingRepo = FollowingDB
    private val analysisRepo = AnalysisDB

    private var followers : MutableLiveData<List<FollowerData>> = MutableLiveData()
    private var followings : MutableLiveData<List<FollowingData>> = MutableLiveData()

    fun getFollowersList(): MutableLiveData<List<FollowerData>>{
        return followers
    }

    fun getFollowingsList(): MutableLiveData<List<FollowingData>> {
        return followings
    }


    fun insertFollower(data: FollowerData,context: Context){
        followersRepo.getDB(context)!!.followersDao().insert(data)
    }

    fun insertFollowing(data: FollowingData, context: Context){
        followingRepo.getDB(context)!!.followingDao().insert(data)
    }

    fun insertAnalysedData(data: AnalysisData,context: Context){
        analysisRepo.getDB(context)!!.analysisDao().insert(data)
    }

    fun getFollowers(context: Context, saveKey:String) {
        followers.value = followersRepo.getDB(context)!!.followersDao().getAllFollowers(saveKey)
    }

    fun getFollowings(context: Context, saveKey:String) {
        followings.value = followingRepo.getDB(context)!!.followingDao().getAllFollowing(saveKey)
    }

}