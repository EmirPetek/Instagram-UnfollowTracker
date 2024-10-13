package com.emirpetek.instagramunfollowtracker.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.emirpetek.instagramunfollowtracker.data.roomData.FollowerData

@Database(entities = [FollowerData::class], version = 1)
@TypeConverters(Converters::class)

abstract class FollowersDB : RoomDatabase(){

    abstract fun followersDao(): FollowersDao

    companion object{

        private var instance : FollowersDB? = null

        fun getDB(context: Context): FollowersDB? {
            if (instance == null){
                synchronized(FollowersDB::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FollowersDB::class.java,
                        "followers.db"
                    ).allowMainThreadQueries().build()
                }

            }
            return instance

        }


    }

}