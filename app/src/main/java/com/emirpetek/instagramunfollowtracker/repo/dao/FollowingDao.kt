package com.emirpetek.instagramunfollowtracker.repo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowerData
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowingData

@Dao
interface FollowingDao {

    @Insert
    fun insert(following:FollowingData)

    @Query("SELECT * FROM followings where saveKey = :id")
    fun getAllFollowing(id: String): List<FollowerData>


}