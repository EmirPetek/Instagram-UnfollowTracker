package com.emirpetek.instagramunfollowtracker.ui.fragment.makeAnalysis

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowerData
import com.emirpetek.instagramunfollowtracker.repo.FollowersDB

class MakeAnalysisViewModel : ViewModel() {
    val followersRepo = FollowersDB

    private var followers : MutableLiveData<List<FollowerData>> = MutableLiveData()

    fun getFollowersList(): MutableLiveData<List<FollowerData>>{
        return followers
    }


    fun insert(data: FollowerData,context: Context){
        followersRepo.getDB(context).followersDao().insert(data)
    }

    fun getFollowers(context: Context) {
        followers.value = followersRepo.getDB(context).followersDao().getAllFollowers()
    }

}