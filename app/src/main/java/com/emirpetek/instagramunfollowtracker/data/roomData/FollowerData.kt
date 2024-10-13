package com.emirpetek.instagramunfollowtracker.data.roomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emirpetek.instagramunfollowtracker.data.DataItem

@Entity(tableName = "followers")
class FollowerData(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "followers")
    var followers: List<DataItem>


) {
}