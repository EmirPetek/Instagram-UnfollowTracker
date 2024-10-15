package com.emirpetek.instagramunfollowtracker.repo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emirpetek.instagramunfollowtracker.data.roomData.AnalysisData
import com.emirpetek.instagramunfollowtracker.repo.Converters
import com.emirpetek.instagramunfollowtracker.repo.dao.AnalysisDao

@Database(entities = [AnalysisData::class], version = 1)
@TypeConverters(Converters::class)
abstract class AnalysisDB: RoomDatabase() {
    abstract fun analysisDao(): AnalysisDao

    companion object{
        private var instance: AnalysisDB? = null


        fun getDB(context: Context): AnalysisDB?{

            if (instance == null){
                synchronized(AnalysisDB::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnalysisDB::class.java,
                        "analysis.db"
                    ).allowMainThreadQueries().build()
                }

            }
            return instance
        }

    }

}