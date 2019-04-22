package com.boni.neon.ui.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.boni.neon.R
import com.boni.neon.ext.hide
import com.boni.neon.ext.show
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.rounded_image_view.view.*

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
        loadImage(url)
    }

    fun setName(name: String) {
        setText(name)
    }

    fun setImage(url: String) {
        loadImage(url)
    }

    private fun loadImage(url: String?) {
        if (url.isNullOrEmpty()) {
            return
        }
        roundedImage.show()

        Picasso.get()
            .load(url)
            .transform(CircleTransform())
            .into(roundedImage)
    }

    private fun setText(text: String?) {
        if(text.isNullOrEmpty()) {
            name.hide()
            return
        }

        name.show()

        with(text.split(" ")) {
            name.text = if(count() > 1) {
                val firstName = get(0)[0].toString()
                val lastName = get(1)[0].toString()

                "$firstName$lastName"
            } else {
                get(0)[0].toUpperCase().toString()
            }
        }
    }

    private fun setBorder(isFullBorder: Boolean) {
        if(isFullBorder) {
            border.setImageResource(R.drawable.full_painted_border)
        } else {
            border.setImageResource(R.drawable.partial_painted_border)
        }
    }

    inner class CircleTransform : Transformation {
        override fun transform(source: Bitmap): Bitmap {
            val size = Math.min(source.width, source.height)
            val borderWith = resources.getDimension(R.dimen.border_width)

            val x = (source.width - size) / 2
            val y = (source.height - size) / 2

            val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
            if (squaredBitmap != source) {
                source.recycle()
            }

            val bitmap = Bitmap.createBitmap(size, size, source.config)

            val canvas = Canvas(bitmap)
            val paint = Paint()
            val shader = BitmapShader(squaredBitmap,
                Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.shader = shader
            paint.isAntiAlias = true

            val r = (size / 2f)
            canvas.drawCircle(r, r, r - borderWith, paint)

            squaredBitmap.recycle()
            return bitmap
        }

        override fun key(): String {
            return "circle"
        }
    }
}