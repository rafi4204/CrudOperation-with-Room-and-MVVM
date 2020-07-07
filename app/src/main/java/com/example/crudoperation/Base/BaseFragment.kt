package com.example.crudoperation.Base

import android.content.Context
import android.content.Intent
import android.util.Log.d
import android.util.Log.e
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.crudoperation.MainActivity
import com.example.crudoperation.R

import java.util.logging.Logger

open class BaseFragment : Fragment() {
    fun replaceFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
        }
        transaction?.commit()
    }


    fun redirectToDashboard() {
        val intent = Intent(activity!!, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TOP
                or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun closeKeyboard() {
        try {
            val view = activity!!.currentFocus
            if (view != null) {
                val imm =
                    activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}