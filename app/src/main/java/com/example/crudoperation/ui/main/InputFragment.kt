package com.example.crudoperation.ui.main

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.example.crudoperation.AppDB
import com.example.crudoperation.R
import com.example.crudoperation.User


class InputFragment : Fragment() {


    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = InputFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.input_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val db = Room.databaseBuilder(context!!, AppDB::class.java, "database-name").build()

      //  viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val user=User(1,"rafi","ah")

        val intent=Intent()
        val bundle= bundleOf(Pair("key",2))


        intent.putExtra("get key",bundle)
        startActivityForResult(intent,101)

    }


}
