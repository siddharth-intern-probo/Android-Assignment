package com.probo.proboassignment1.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.probo.proboassignment1.R

class ImageActivity : AppCompatActivity() {

    companion object {
        const val READ_STORAGE_PERMISSION_CODE = 1
        const val PICK_IMAGE_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val selectImageButton = findViewById<AppCompatButton>(R.id.btn_image_select)
        selectImageButton.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this@ImageActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery(this@ImageActivity)
            }
            else {
                ActivityCompat.requestPermissions(
                    this@ImageActivity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_STORAGE_PERMISSION_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == READ_STORAGE_PERMISSION_CODE) {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery(this@ImageActivity)
            }
            else {
                Toast.makeText(this@ImageActivity,
                    "Permissions Denied. Please Allow It From Settings", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pickImageFromGallery(activity: ImageActivity) {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            var selectedImage = data.getData()
            val intent = Intent(this@ImageActivity, MainActivity::class.java)

            val gotBundle = getIntent().extras;

            val bundle = Bundle()
            bundle.putString("email", gotBundle?.getString("email"))
            bundle.putString("dob", gotBundle?.getString("dob"))
            bundle.putString("password", gotBundle?.getString("password"))
            bundle.putString("selectedImage", selectedImage.toString())

            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

}