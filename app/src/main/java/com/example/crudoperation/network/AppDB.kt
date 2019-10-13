package com.example.crudoperation.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crudoperation.model.User

@Database(entities = arrayOf(User::class), version = 2)
abstract class AppDB : RoomDatabase() {

    companion object {

        fun getInstance(context: Context): AppDB {
            return Room.databaseBuilder(context, AppDB::class.java, "UserBase.DB")
                .allowMainThreadQueries().build()

        }
    }

    abstract fun userDao(): UserDao
}