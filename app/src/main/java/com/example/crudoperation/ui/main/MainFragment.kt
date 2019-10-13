package com.example.crudoperation.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudoperation.Base.BaseFragment
import com.example.crudoperation.R
import com.example.crudoperation.model.User
import com.example.crudoperation.UserAdapter
import com.example.crudoperation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn.setOnClickListener {
            replaceFragment(InputFragment())
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.setUser()
        recycler_view.layoutManager = LinearLayoutManager(context)

        viewModel.userData.observe(this, Observer<List<User>> {
            recycler_view.adapter = UserAdapter(it)
        })


    }




}
