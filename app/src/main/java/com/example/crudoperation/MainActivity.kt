package com.example.crudoperation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crudoperation.ui.main.DashBoardFragment
import com.example.crudoperation.ui.main.InputFragment
import com.example.crudoperation.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DashBoardFragment.newInstance())
                .commitNow()
        }
    }

    fun clearBackStackInclusive(tag: String) {
        supportFragmentManager.popBackStack(tag, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}
