package com.example.cameralightsaction

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    // Request code for camera intent
    // Secret handshake that authenticates for correct result
    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Click listener set on Button to launch camera activity
        findViewById<FloatingActionButton>(R.id.fabCapture).setOnClickListener {
            dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun dispatchTakePictureIntent(requestCode: Int) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, requestCode)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check if requestCode matches REQUEST_IMAGE_CAPTURE value
        // Check if result was OK
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Unpack Intent data to retrieve image as Bitmap
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // Bind Bitmap image to ImageView
            findViewById<ImageView>(R.id.ivImage).setImageBitmap(imageBitmap)
        }
    }
}