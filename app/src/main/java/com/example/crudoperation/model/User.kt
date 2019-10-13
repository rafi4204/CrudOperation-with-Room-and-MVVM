package com.example.crudoperation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    @ColumnInfo(name = "firstname") var firstName: String = "", @ColumnInfo(name = "lastname") var lastName: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}