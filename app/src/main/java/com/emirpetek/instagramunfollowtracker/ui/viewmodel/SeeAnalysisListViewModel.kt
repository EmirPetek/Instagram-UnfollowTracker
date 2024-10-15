package com.emirpetek.instagramunfollowtracker.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirpetek.instagramunfollowtracker.data.roomData.AnalysisData
import com.emirpetek.instagramunfollowtracker.repo.database.AnalysisDB

class SeeAnalysisListViewModel: ViewModel() {
    private val db = AnalysisDB

    private val analysedData : MutableLiveData<List<AnalysisData>> = MutableLiveData()

    fun getData() : MutableLiveData<List<AnalysisData>>{ return analysedData }

    fun getAnalysis(mContext:Context) {
        analysedData.value = db.getDB(mContext)!!.analysisDao().getAllFollowing()
    }
}