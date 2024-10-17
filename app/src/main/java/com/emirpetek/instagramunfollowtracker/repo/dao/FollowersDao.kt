package com.emirpetek.instagramunfollowtracker.repo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowerData

@Dao
interface FollowersDao {


    @Insert
    fun insert(followers: FollowerData)

    @Query("SELECT * FROM followers where saveKey = :id")
    fun getAllFollowers(id: String): List<FollowerData>
}