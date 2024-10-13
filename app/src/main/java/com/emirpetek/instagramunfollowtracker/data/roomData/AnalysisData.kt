package com.emirpetek.instagramunfollowtracker.data.roomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.emirpetek.instagramunfollowtracker.data.DataItem

@Entity(tableName = "analysedData")
class AnalysisData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "followers")
    var analysedDatas: List<DataItem> = listOf(),


    @ColumnInfo(name = "saveKey")
    var saveKey: String
) {
}