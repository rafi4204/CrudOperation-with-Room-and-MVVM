package com.example.crudoperation

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDB :RoomDatabase() {
    abstract fun userDao(): UserDao
}