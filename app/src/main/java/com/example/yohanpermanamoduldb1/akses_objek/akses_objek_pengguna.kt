package com.example.yohanpermanamoduldb1.akses_objek

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yohanpermanamoduldb1.model.pengguna

@Dao
interface akses_objek_pengguna {

    @Insert
    fun insert(users: pengguna)

    @Update
    fun update(users: pengguna)

    @Delete
    fun delete(users: pengguna)

    @Query("select * from users_table order by fullname desc")
    fun getAllUsers() : LiveData<List<pengguna>>

    @Query("delete from users_table")
    fun deleteAllUsers()
}