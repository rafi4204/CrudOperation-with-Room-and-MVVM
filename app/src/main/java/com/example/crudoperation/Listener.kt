package com.example.crudoperation

import com.example.crudoperation.model.User

interface Listener {
    fun updateListener(user: User?)
    fun deleteListener(user: User?)
}