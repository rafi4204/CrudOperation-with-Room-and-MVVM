package com.example.crudoperation

import com.example.crudoperation.model.User

interface Listener {
    fun adapterListener(user: User?)
}