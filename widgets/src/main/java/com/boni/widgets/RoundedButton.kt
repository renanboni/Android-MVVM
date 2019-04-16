package com.boni.widgets

import android.content.Context
import android.graphics.Bitmap
import com.squareup.picasso.Transformation
import android.graphics.Shader.TileMode
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.boni.neon.ext.hide
import com.boni.neon.ext.show
import kotlinx.android.synthetic.main.rounded_image_view.view.*
import android.util.TypedValue
import com.boni.neon.R
import com.squareup.picasso.Picasso

class CircularImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var fullBorder: Boolean = false
    private var text: String? = ""
    private var url: String? = ""

    init {
        LayoutInflater.from(context).inflate(
            R.layout.rounded_image_view,
            this
        )

        attrs?.let {
            val typedArray = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.CircularImageView
            )

            fullBorder = typedArray.getBoolean(R.styleable.CircularImageView_fullBorder, false)
            text = typedArray.getString(R.styleable.CircularImageView_text)
            url = typedArray.getString(R.styleable.CircularImageView_imgUrl)

            typedArray.recycle()
        }

        setText(text)
        setBorder(fullBorder)
        // setImage(url)
    }

    private fun setImage(url: String?) {
        if(url.isNullOrEmpty()) {
            fullPaintedBorder.hide()
            partialPaintedBorder.hide()
            return
        }

        val picasso = Picasso
            .get()
            .load(url)
            .transform(CircleTransform())

        if(fullBorder) {
            fullPaintedBorder.show()
            picasso.into(fullPaintedBorder)
        } else {
            partialPaintedBorder.show()
            picasso.into(partialPaintedBorder)
        }
    }

    private fun setText(text: String?) {
        if(text.isNullOrEmpty()) {
            name.hide()
            return
        }

        name.show()

        with(text.split(" ")) {
            name.text = if(count() > 1) {
                (get(0)[0].toString() + get(1)[0]).toUpperCase()
            } else {
                get(0)[0].toUpperCase().toString()
            }
        }
    }

    private fun setBorder(isFullBorder: Boolean) {
        if(isFullBorder) {
            fullPaintedBorder.show()
            partialPaintedBorder.hide()
        } else {
            fullPaintedBorder.hide()
            partialPaintedBorder.show()
        }
    }

    inner class CircleTransform : Transformation {
        override fun transform(source: Bitmap?): Bitmap? {
            source?.let {
                if (it.isRecycled) {
                    return@let
                }

                val width = source.width + R.dimen.border_width
                val height = source.height + R.dimen.border_width

                val canvasBitmap = Bitmap.createBitmap(
                    width,
                    height,
                    Bitmap.Config.ARGB_8888
                )

                val shader = BitmapShader(
                    source,
                    TileMode.CLAMP,
                    TileMode.CLAMP
                )

                val paint = Paint().apply {
                    isAntiAlias = true
                    setShader(shader)
                }

                val canvas = Canvas(canvasBitmap)
                val radius = if (width > height) height.toFloat() / 2f else width.toFloat() / 2f
                canvas.drawCircle(
                    (width / 2).toFloat(),
                    (height / 2).toFloat(),
                    radius,
                    paint
                )

                if (canvasBitmap != source) {
                    source.recycle()
                }

                return canvasBitmap
            }
            return null
        }

        override fun key() = "circle"
    }
}