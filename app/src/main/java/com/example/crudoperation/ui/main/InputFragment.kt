package com.example.crudoperation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.example.crudoperation.AppDB
import com.example.crudoperation.R


class InputFragment : Fragment() {


    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.input_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val db = Room.databaseBuilder(context!!, AppDB::class.java, "database-name").build()

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }
}
