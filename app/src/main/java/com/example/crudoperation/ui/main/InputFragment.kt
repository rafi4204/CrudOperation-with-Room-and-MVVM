package com.example.crudoperation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.crudoperation.Base.BaseFragment
import com.example.crudoperation.R
import com.example.crudoperation.model.User
import com.example.crudoperation.resitory.UserRepository
import com.example.crudoperation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.input_fragment.*


class InputFragment :  BaseFragment() {


    private lateinit var viewModel: MainViewModel
lateinit var repository: UserRepository
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

    update_btn.setOnClickListener {
        if(firstname==null||lastname==null)
            Toast.makeText(context,"Please enter input",Toast.LENGTH_SHORT).show()
        else{
            repository= UserRepository(context!!)

            val user= User(
                firstname.text.toString(),
                lastname.text.toString()
            )
            repository.setUserData(user)

            replaceFragment(MainFragment())

        }


    }






    }


}
