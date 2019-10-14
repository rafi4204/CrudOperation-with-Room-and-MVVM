package com.example.crudoperation.network


import androidx.room.*
import com.example.crudoperation.model.User
import io.reactivex.Observable

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): Observable<List<User>>

   /* @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>*/

  /*  @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User*/

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User?)
    @Update
    fun update(user:User?)
}