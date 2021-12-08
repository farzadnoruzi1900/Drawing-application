package com.example.kiddrawing

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.size

/*it is good to know that the method you declare inside of the view class called DrawView
is accessible via the object of that view that you
create it via the id drawingView = findViewById(R.id.view)*/

class MainActivity : AppCompatActivity() {
   private var drawingView: Drawview? = null
   private var brushDialogButton : ImageButton?=null

   private var mImageButtonCurrentPaint : ImageButton?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.view)
        brushDialogButton=findViewById(R.id.ib_brush_size_dialog)
//        in hwe are using the method we declared to set the brush size .
        drawingView?.setBrushSize(20.toFloat())
//        in here we use the dialog to set the brushSize
        brushDialogButton?.setOnClickListener {
            showBrushSizeSelectorDialog()
        }
        /* this is how we are gonna access to the image button inside the linearLayout
    because linearLayout is working like an ArrayList and give index to all its members
    so we access the members by their indexis so as you know in index zero there is black color  */
        val linearLayout = findViewById<LinearLayout>(R.id.ll_color_ib)
       /* it is amazing we set this imageButton as default as a select brush color and then we use the
        method colorClickon to change this */
        mImageButtonCurrentPaint=linearLayout[0] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.pallet_press
            )
        )
      /*  and just with that piece of code below we are saying when it is pressed then set its drawable
                to the press drawable .*/




    }

    private fun showBrushSizeSelectorDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size : ")
        val btnSmall = brushDialog.findViewById<ImageButton>(R.id.ib_small_size)
        btnSmall.setOnClickListener {
            drawingView?.setBrushSize(10.toFloat())
            brushDialog.dismiss()
//            remove the dialog after that
        }
        val btnMedium = brushDialog.findViewById<ImageButton>(R.id.ib_medium_size)
        btnMedium.setOnClickListener {
            drawingView?.setBrushSize(20.toFloat())
            brushDialog.dismiss()

        }
        val btnLarge = brushDialog.findViewById<ImageButton>(R.id.ib_big_size)
        btnLarge.setOnClickListener {
            drawingView?.setBrushSize(30.toFloat())
            brushDialog.dismiss()

        }
        brushDialog.show()

    }
     fun colorClickOn(view : View){
        if(view!==mImageButtonCurrentPaint){
            val imageButton=view as ImageButton
         /*   do not forget that each color location is a string and thats how we get it from tag and
                    reassign to the method we declared in the drawingView*/
            val colorTag=imageButton.tag.toString()
            drawingView?.setBrushColor(colorTag)
            imageButton.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.pallet_press)
            )
            mImageButtonCurrentPaint?.setImageDrawable(ContextCompat.getDrawable(
                this, R.drawable.pallet_normal)
            )

            mImageButtonCurrentPaint=view
        }
    }
}


