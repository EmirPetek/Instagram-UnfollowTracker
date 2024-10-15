package com.emirpetek.instagramunfollowtracker.repo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowingData
import com.emirpetek.instagramunfollowtracker.repo.Converters
import com.emirpetek.instagramunfollowtracker.repo.dao.FollowingDao

@Database(entities = [FollowingData::class], version = 1)
@TypeConverters(Converters::class)
abstract class FollowingDB: RoomDatabase() {

    abstract fun followingDao(): FollowingDao

    companion object{
        private var instance : FollowingDB? = null

        fun getDB(context: Context): FollowingDB? {
            if (instance == null){
                synchronized(FollowingDB::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FollowingDB::class.java,
                        "following.db"
                    ).allowMainThreadQueries().build()
                }

            }
            return instance

        }

    }
}