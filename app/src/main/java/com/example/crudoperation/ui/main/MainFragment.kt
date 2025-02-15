package com.example.crudoperation.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudoperation.Base.BaseFragment
import com.example.crudoperation.Listener
import com.example.crudoperation.MainActivity
import com.example.crudoperation.R
import com.example.crudoperation.model.User
import com.example.crudoperation.UserAdapter
import com.example.crudoperation.resitory.UserRepository
import com.example.crudoperation.utils.AppHelper
import com.example.crudoperation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment(), Listener {


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


        activity?.title = "Employee List"
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setUser()
        recycler_view.layoutManager = LinearLayoutManager(context)

        viewModel.userData.observe(viewLifecycleOwner, Observer<List<User>> {
            if (it.isEmpty())
                msg.visibility = View.VISIBLE
            recycler_view.adapter = UserAdapter(it)
            (recycler_view.adapter as UserAdapter).listener = this
        })

        btn_back.setOnClickListener {
            //replaceFragment(DashBoardFragment())
            redirectToDashboard()
        }

        getBackButton()
    }

    private fun getBackButton() {
        view!!.isFocusableInTouchMode = true
        view!!.requestFocus()
        view!!.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            Log.i(tag, "keyCode: $keyCode")
            if (keyCode == KeyEvent.KEYCODE_BACK) {
               redirectToDashboard()
                return@OnKeyListener true
            }
            false
        })
    }
    override fun updateListener(user: User?) {
        val bundle = bundleOf("key" to user, AppHelper.CHECKED_ID to 1)
        val frag = InputFragment()
        frag.arguments = bundle
        replaceFragment(frag)
    }

    override fun deleteListener(user: User?) {
        val repository = UserRepository(context!!)
        repository.deleteUser(user)
        viewModel.setUser()

    }

}
