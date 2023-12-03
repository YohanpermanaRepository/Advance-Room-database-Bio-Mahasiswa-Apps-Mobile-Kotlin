package com.example.yohanpermanamoduldb1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class pengguna(
    @ColumnInfo(name = "fullname")
    val fullName : String,
    val universitas : String,
    val nim : String,
    val email : String,
    val dob : String,
    val gender : String,
    @PrimaryKey(autoGenerate = false) val id : Int? = null
)