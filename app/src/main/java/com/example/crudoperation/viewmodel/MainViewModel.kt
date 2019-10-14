package com.example.crudoperation.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.crudoperation.model.User
import com.example.crudoperation.resitory.UserRepository

class MainViewModel(application:Application) : AndroidViewModel(application) {
    var userData= MutableLiveData<List<User>>()
    lateinit var userRepository: UserRepository
    @SuppressLint("CheckResult")
    fun setUser() {

        userRepository = UserRepository(getApplication())
        userRepository.getUserData()?.subscribe {
            userData.apply {
                this.value = it
            }
            Log.d("2", userData.value?.size.toString())
        }
    }


}
