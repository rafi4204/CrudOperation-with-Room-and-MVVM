package com.example.crudoperation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
class User(
    @ColumnInfo(name = "firstname") var firstName: String = "",
    @ColumnInfo(name = "lastname") var lastName: String,
    @ColumnInfo(name = "age") var age: String,
    @ColumnInfo(name = "gender") var gender: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray):Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}