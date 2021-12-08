package com.example.kiddrawing

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View

class Drawview(context: Context, attr: AttributeSet) : View(context, attr) {

    private var mdrawPath: CustomPath? = null
    private var mConvasBitMap: Bitmap? = null
    private var mDrawPaint: Paint? = null
    private var mConvasPaint: Paint? = null
    private var mBrushSize: Float = 0.toFloat()
    private var color = Color.BLACK
    private var canvas: Canvas? = null
    private val mpaths=ArrayList<CustomPath>()

    init {
        setUpDrawing()
    }

    /*
        The Paint class holds the style and color information about how to draw geometries, text and bitmaps
    */
    private fun setUpDrawing() {
        mDrawPaint = Paint()
        mdrawPath = CustomPath(color, mBrushSize)
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
       // mBrushSize = 20.toFloat()
       /* we define a method called setBrushsize and with help of that we are gonna set the brush size in our
                main activity*/
    }

    //    when our drawing view display this method will automatically run and set the configuration for us
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        here is the place we declare our bitmap setting which we declare in form of canvas var
        mConvasBitMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
//        the config ARGB_8888 will gives 256bit of color to each of our pixle on screen
//        ARGB_8888 means each pixle can have color of 256 bit which means 193800000 different color
//        can be chosen for each individual pixel
        canvas = Canvas(mConvasBitMap!!)
    }

    //    it runs when we want to draw something and it is exactly what we do we draw on our canvas
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mConvasBitMap!!, 0f, 0f, mConvasPaint)
        for(path in mpaths){
            mDrawPaint!!.strokeWidth = path.brushthickness
            mDrawPaint!!.color = path.colokr
            canvas.drawPath(path,mDrawPaint!!)
        }
        if (!mdrawPath!!.isEmpty) {
            mDrawPaint!!.strokeWidth = mdrawPath!!.brushthickness
            mDrawPaint!!.color = mdrawPath!!.colokr
            canvas.drawPath(mdrawPath!!, mDrawPaint!!)
        }
    }

    //    in here we are gonna define what happen when we touch this event and as you know we wanna to draw something
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val touchx = event?.x
        val touchy = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mdrawPath!!.colokr = color
                mdrawPath!!.brushthickness = mBrushSize

                mdrawPath!!.reset()
                mdrawPath!!.moveTo(touchx!!, touchy!!)
            }

            MotionEvent.ACTION_MOVE -> {
                mdrawPath!!.lineTo(touchx!!, touchy!!)
            }
            MotionEvent.ACTION_UP -> {
                mpaths.add(mdrawPath!!)
                mdrawPath = CustomPath(color, mBrushSize)
            }
            else -> {
                return false
            }
        }
        invalidate()

        return true

    }

    /*we can not assign the value we get as parameter directly to the brush size because in this
    case it can not adjust it self on different screen and devices which are gonna use this app
    so we use this method called TypedValue.applyDimension()*/
    fun setBrushSize(size : Float){
        mBrushSize=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,size,
            resources.displayMetrics)
        mDrawPaint!!.strokeWidth=mBrushSize
    }

    fun setBrushColor(newColor: String){
        color=Color.parseColor(newColor)
        mDrawPaint!!.color=color
    }
    internal inner class CustomPath(var colokr: Int, var brushthickness: Float) : Path() {

    }
}


