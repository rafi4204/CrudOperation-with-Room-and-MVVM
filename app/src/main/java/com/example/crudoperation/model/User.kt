package com.example.crudoperation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
class User(@ColumnInfo(name = "firstname") var firstName: String = "", @ColumnInfo(name = "lastname") var lastName: String) :
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}