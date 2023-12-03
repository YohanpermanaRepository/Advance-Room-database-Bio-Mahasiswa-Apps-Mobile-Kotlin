package com.example.yohanpermanamoduldb1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.yohanpermanamoduldb1.model.pengguna
import com.example.yohanpermanamoduldb1.repository.yohan_function_pengguna

class UsersViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository = yohan_function_pengguna(application)
    private val allUsers = repository.getAllUsers()

    fun insert(users: pengguna)
    {
        repository.insert(users)
    }

    fun update(users: pengguna)
    {
        repository.update(users)
    }

    fun delete(users: pengguna)
    {
        repository.delete(users)
    }

    fun deleteAllUsers(){
        repository.deleteAllUsers()
    }

    fun getAllUsers() : LiveData<List<pengguna>>{
        return allUsers
    }
}