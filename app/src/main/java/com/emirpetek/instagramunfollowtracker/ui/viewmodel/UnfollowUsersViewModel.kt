package com.emirpetek.instagramunfollowtracker.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirpetek.instagramunfollowtracker.data.roomData.AnalysisData
import com.emirpetek.instagramunfollowtracker.repo.database.AnalysisDB

class UnfollowUsersViewModel : ViewModel() {

    private val db = AnalysisDB

    private val data : MutableLiveData<List<AnalysisData>> = MutableLiveData()

    fun getUnfUsers() : MutableLiveData<List<AnalysisData>> {
        return data
    }

    fun getUnfUsersDB(context: Context,saveKey:String){
        data.value = db.getDB(context)!!.analysisDao().getUnfUsers(saveKey)
    }


}