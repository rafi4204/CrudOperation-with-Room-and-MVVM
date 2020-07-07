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
import android.view.KeyEvent
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


class InputFragment : BaseFragment() {
    var tempUser: User? = null
    var checkedId = -1
    private lateinit var viewModel: MainViewModel
    lateinit var repository: UserRepository

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    var permissionlist = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    var permissionCode = 10
    var cameraRequestCode = 100
    var galleryRequestCode = 101
    lateinit var selectedUri: Uri
    var image: ByteArray? = null


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
        arguments?.getInt(AppHelper.CHECKED_ID, -1)?.let {
            checkedId = it
        }
        activity?.title = "Insert Employee Info"
        btn_back.setOnClickListener { //replaceFragment(MainFragment())
            if (checkedId == 1)
                activity?.onBackPressed()
            else
                redirectToDashboard()
        }
        if (checkedId == 1) {
            arguments?.getSerializable("key")?.let {
                tempUser = it as User?
            }
            activity?.title = "Update Employee Info"
            update_btn.text = getString(R.string.update)
            firstname.setText(tempUser?.firstName)
            lastname.setText(tempUser?.lastName)
            age.setText(tempUser?.age)
            image = tempUser?.image
            iv.setImageBitmap(tempUser?.image?.size?.let {
                BitmapFactory.decodeByteArray(
                    tempUser?.image,
                    0,
                    it
                )
            })
        }
        btn_img.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context!!,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context!!,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissionlist, permissionCode)
            } else {
                showPickImageDialog()
            }

        }
        BitmapHelper.skipPermission()
        update_btn.setOnClickListener {
            if (firstname.text.toString() == "" || lastname.text.toString() == "") {
                Toast.makeText(context, "Please enter input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (image == null) {
                Toast.makeText(context, "Please upload image", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                repository = UserRepository(context!!)

                if (checkedId == 1) {
                    Log.d("334", tempUser?.firstName)

                    tempUser?.firstName = firstname.text.toString()
                    tempUser?.lastName = lastname.text.toString()
                    tempUser?.age = age.text.toString()
                    tempUser?.gender = gender.selectedItem.toString()
                    tempUser?.image = image as ByteArray
                    repository.updateUser(tempUser)
                } else {

                    val user = image?.let { it1 ->
                        User(
                            firstname.text.toString(),
                            lastname.text.toString(),
                            age.text.toString(),
                            gender.selectedItem.toString(),
                            it1
                        )
                    }
                    user?.let { it1 -> repository.setUserData(it1) }
                }
                replaceFragment(MainFragment())
            }
        }
        getBackButton()
    }

    private fun getBackButton() {
        view!!.isFocusableInTouchMode = true
        view!!.requestFocus()
        view!!.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            Log.i(tag, "keyCode: $keyCode")
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (checkedId == 1)
                    activity?.onBackPressed()
                else
                    redirectToDashboard()
                return@OnKeyListener true
            }
            false
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        var isAllGrand = false
        if (requestCode == permissionCode) {
            for (i in grantResults) {
                isAllGrand = i == PackageManager.PERMISSION_GRANTED
            }

            if (isAllGrand) {
                showPickImageDialog()
            }
        }

    }


    private fun showPickImageDialog() {
        val pickPhoto = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(pickPhoto, galleryRequestCode)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == galleryRequestCode && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                val bitmap = BitmapHelper.handleSamplingAndRotationBitmap(activity, uri)
                image = encodeImage(bitmap)
                val image2 = image?.size?.let { BitmapFactory.decodeByteArray(image, 0, it) }
                iv.setImageBitmap(image2)

            }

        }
    }

    private fun getMime(uri: Uri): String {
        val cr = activity?.contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr?.getType(uri))!!
    }


    private fun encodeImage(bm: Bitmap): ByteArray? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }
}
