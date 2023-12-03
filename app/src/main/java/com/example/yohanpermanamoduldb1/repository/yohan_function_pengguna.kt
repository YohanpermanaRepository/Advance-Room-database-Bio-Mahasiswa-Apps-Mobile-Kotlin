package com.example.yohanpermanamoduldb1.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.yohanpermanamoduldb1.akses_objek.akses_objek_pengguna
import com.example.yohanpermanamoduldb1.database.room_database_pengguna
import com.example.yohanpermanamoduldb1.model.pengguna
import com.example.yohanpermanamoduldb1.utils.subscribeOnBackground

class yohan_function_pengguna(application: Application)
{
    private var usersDao : akses_objek_pengguna
    private var allUsers : LiveData<List<pengguna>>

    private val database = room_database_pengguna.getDatabaseUsers(application)

    init {
        usersDao = database.usersDao()
        allUsers = usersDao.getAllUsers()
    }

    // insert data
    fun insert(users: pengguna)
    {
        subscribeOnBackground {
            usersDao.insert(users)
        }
    }

    // update data
    fun update(users: pengguna){
        subscribeOnBackground {
            usersDao.update(users)
        }
    }

    // delete data
    fun delete(users: pengguna){
        subscribeOnBackground {
            usersDao.delete(users)
        }
    }

    // delete semua data
    fun deleteAllUsers(){
        subscribeOnBackground {
            usersDao.deleteAllUsers()
        }
    }
    // menampilkan data
    fun getAllUsers() : LiveData<List<pengguna>>{
        return allUsers
    }
}