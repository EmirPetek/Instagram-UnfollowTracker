package com.emirpetek.instagramunfollowtracker.ui.fragment.makeAnalysis

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirpetek.instagramunfollowtracker.data.DataItem
import com.emirpetek.instagramunfollowtracker.data.roomData.AnalysisData
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowerData
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowingData
import com.emirpetek.instagramunfollowtracker.repo.AnalysisDB
import com.emirpetek.instagramunfollowtracker.repo.FollowersDB
import com.emirpetek.instagramunfollowtracker.repo.FollowingDB

class MakeAnalysisViewModel : ViewModel() {
    private val followersRepo = FollowersDB
    private val followingRepo = FollowingDB
    private val analysisRepo = AnalysisDB

    private var followers : MutableLiveData<List<FollowerData>> = MutableLiveData()

    fun getFollowersList(): MutableLiveData<List<FollowerData>>{
        return followers
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

    fun getFollowers(context: Context) {
        followers.value = followersRepo.getDB(context)!!.followersDao().getAllFollowers()
    }

}