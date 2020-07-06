package com.example.crudoperation.ui.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.CaseMap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.crudoperation.Base.BaseFragment
import com.example.crudoperation.R
import com.example.crudoperation.model.User
import com.example.crudoperation.resitory.UserRepository
import com.example.crudoperation.utils.AppHelper
import com.example.crudoperation.utils.BitmapHelper
import com.example.crudoperation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.dashboard_fragment.*
import kotlinx.android.synthetic.main.input_fragment.*
import java.io.ByteArrayOutputStream


class DashBoardFragment : BaseFragment() {
    var tempUser: User? = null
    var checkedId = -1
    private lateinit var viewModel: MainViewModel
    lateinit var repository: UserRepository



    companion object {
        fun newInstance() = DashBoardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.title=getString(R.string.emp_management)
        btn_emp_list.setOnClickListener {
            replaceFragment(MainFragment())
        }

       btn_insert.setOnClickListener {
            replaceFragment(InputFragment())
        }

    }



}
