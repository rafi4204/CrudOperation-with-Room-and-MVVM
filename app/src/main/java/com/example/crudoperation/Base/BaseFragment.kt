package com.example.crudoperation.Base

import androidx.fragment.app.Fragment
import com.example.crudoperation.R

open class BaseFragment: Fragment() {
     fun replaceFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
        }
        transaction?.commit()
    }
}