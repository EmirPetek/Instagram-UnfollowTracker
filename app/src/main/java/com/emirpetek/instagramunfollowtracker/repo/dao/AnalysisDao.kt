package com.emirpetek.instagramunfollowtracker.repo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirpetek.instagramunfollowtracker.data.roomData.AnalysisData

@Dao
interface AnalysisDao {

    @Insert
    fun insert(data: AnalysisData)

    @Query("Select * from analysedData")
    fun getAllFollowing(): List<AnalysisData>

    @Query("SELECT * FROM analysedData where saveKey = :id")
    fun getUnfUsers(id:String): List<AnalysisData>

}