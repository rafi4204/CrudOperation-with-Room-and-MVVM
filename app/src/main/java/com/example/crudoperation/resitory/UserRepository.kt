package com.example.crudoperation.resitory

import android.content.Context
import com.example.crudoperation.network.AppDB
import com.example.crudoperation.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(context: Context) {
    var db: AppDB = AppDB.getInstance(context)


    fun getUserData(): Observable<List<User>>? {

        return db.userDao().getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun setUserData(user: User) {
        db.userDao().insertAll(user)
    }

    fun deleteUser(user: User?) {
        db.userDao().delete(user)
    }

    fun updateUser(tempUser: User?) {
        db.userDao().update(tempUser)

    }

}