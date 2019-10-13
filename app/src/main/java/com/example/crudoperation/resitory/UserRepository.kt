package com.example.crudoperation.resitory

import android.content.Context
import com.example.crudoperation.network.AppDB
import com.example.crudoperation.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(context1: Context) {
    val context = context1
    lateinit var db: AppDB


    fun getUserData(): Observable<List<User>>? {

        db = AppDB.getInstance(context)
        return db.userDao().getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun setUserData(user: User) {
        db = AppDB.getInstance(context)
        db.userDao().insertAll(user)
    }

}