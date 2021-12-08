package com.example.kiddrawing

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var drawingView: Drawview? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.view)
//        in hwe are using the method we declared to set the brush size .
        drawingView?.setBrushSize(20.toFloat())
    }

    private fun showBrushSizeSelectorDialog(){
        val brushDialog=Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size : ")
        val btnSmall=brushDialog

    }
}


