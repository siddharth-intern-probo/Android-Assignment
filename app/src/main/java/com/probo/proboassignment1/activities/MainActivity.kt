package com.probo.proboassignment1.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import com.probo.proboassignment1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREF_NAME = "myPreferences"
    }

    private lateinit var binding: ActivityMainBinding
    private var selectedImageFileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpDetails()
    }

    private fun setUpDetails() {
        val tvEmail = binding.tvEmail
        val tvDOB = binding.tvDob
        val tvPassword= binding.tvPassword
        val tvDisplayImage = binding.displayImage

        val bundle = intent.extras

        selectedImageFileUri = bundle?.getString("selectedImage")?.toUri()

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

