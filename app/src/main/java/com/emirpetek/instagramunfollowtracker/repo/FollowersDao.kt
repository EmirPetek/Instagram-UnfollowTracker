package com.emirpetek.instagramunfollowtracker.repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirpetek.instagramunfollowtracker.data.DataItem
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowerData

@Dao
interface FollowersDao {


    @Insert
    fun insert(followers: FollowerData)

    @Query("SELECT * FROM followers")
    fun getAllFollowers(): List<FollowerData>
}