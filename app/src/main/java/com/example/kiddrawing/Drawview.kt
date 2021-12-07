package com.example.kiddrawing

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Drawview(context: Context, attr: AttributeSet) : View(context, attr) {

    private val drawPath: CustomPath? = null
    private var mConvasBitMap: Bitmap? = null
    private var mDrawPaint: Paint? = null
    private var mConvasPaint: Paint? = null
    private var mBrushSize: Float = 0.toFloat()
    private val color = Color.BLACK
    private var canvas: Canvas? = null

    init {
        setUpDrawing()
    }

    /*
        The Paint class holds the style and color information about how to draw geometries, text and bitmaps
    */
    private fun setUpDrawing() {
        mDrawPaint = Paint()
        mDrawPaint = CustomPath(color, mBrushSize)
        mDrawPaint!!.color = color
        // shows the end of the line
        mDrawPaint!!.strokeCap = Paint.Cap.ROUND
        //The outer edges of a join meet in a circular arc.
        mDrawPaint!!.strokeJoin = Paint.Join.ROUND
//        when we are using stroke style we also need to set the strokeCap and join
        mDrawPaint!!.style = Paint.Style.STROKE
        /*  this flag is for the time we are drawing something and enable the moving the pixle
                  from one side to another place of memory .*/
        mConvasPaint = Paint(Paint.DITHER_FLAG)
        mBrushSize = 20.toFloat()
    }

//    when our drawing view display this method will automatically run and set the configuration for us
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        here is the place we declare our bitmap setting which we declare in form of canvas var
        mConvasBitMap= Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
//        the config ARGB_8888 will gives 256bit of color to each of our pixle on screen
//        ARGB_8888 means each pixle can have color of 256 bit which means 193800000 different color
//        can be chosen for each individual pixel
        canvas=Canvas(mConvasBitMap!!)
    }

//    it runs when we want to draw something and it is exactly what we do we draw on our canvas
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }
    internal inner class CustomPath(var colokr: Int, var brushthickness: Float) : Paint() {

    }
}