package com.probo.proboassignment1.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.probo.proboassignment1.R


class MainActivity : AppCompatActivity() {

    companion object {
        const val READ_STORAGE_PERMISSION_CODE = 1
        const val PICK_IMAGE_REQUEST_CODE = 2
        const val SHARED_PREF_NAME = "myPreferences"
    }

    private var selectedImageFileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpDetails()
    }

    private fun setUpDetails() {
        val tvEmail = findViewById<TextView>(R.id.tv_email)
        val tvDOB = findViewById<TextView>(R.id.tv_dob)
        val tvPassword= findViewById<TextView>(R.id.tv_password)
        val tvDisplayImage = findViewById<ImageView>(R.id.display_image)

        val bundle = intent.extras

        selectedImageFileUri = bundle?.getString("imageURI")?.toUri()

        tvEmail.text = "Email : " + bundle?.getString("email")
        tvDOB.text = "DOB : " + bundle?.getString("dob")
        tvPassword.text = "Password : " + bundle?.getString("password")
        tvDisplayImage.setImageURI(selectedImageFileUri)

        val mySharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
        val editor= mySharedPreferences.edit()
        editor.putString("Email", bundle?.getString("email"))
        editor.putString("DOB", bundle?.getString("dob"))
        editor.putString("Password", bundle?.getString("password"))
        editor.putString("imageURI", selectedImageFileUri.toString())
        editor.apply()
    }

}