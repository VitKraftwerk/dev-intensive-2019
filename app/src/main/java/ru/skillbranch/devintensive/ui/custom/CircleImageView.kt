package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.appcompat.widget.AppCompatImageView
import ru.skillbranch.devintensive.R
import java.lang.Integer.parseInt


/**
 * Реализуй CustomView с названием класса CircleImageView и кастомными xml атрибутами
 * cv_borderColor (цвет границы (format="color") по умолчанию white) и
 * cv_borderWidth (ширина границы (format="dimension") по умолчанию 2dp).
 *
 * CircleImageView должна превращать установленное изображение в круглое изображение с цветной рамкой,
 * у CircleImageView должны быть реализованы методы
 * @Dimension getBorderWidth():Int,
 * setBorderWidth(@Dimension dp:Int),
 * getBorderColor():Int,
 * setBorderColor(hex:String),
 * setBorderColor(@ColorRes colorId: Int).
 * Используй CircleImageView как ImageView для аватара пользователя (@id/iv_avatar)
 */
class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_DIMENSION = 1
        private const val DEFAULT_COLOR = Color.WHITE
    }

    private var borderWidth: Int = DEFAULT_DIMENSION
    private var color: Int = DEFAULT_COLOR

    init{
        if(attrs!= null){
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderWidth = a.getDimension(
                R.styleable.CircleImageView_cv_borderWidth,
                DEFAULT_DIMENSION.toFloat()
            ).toInt()
            color= a.getInt(R.styleable.CircleImageView_cv_borderColor, DEFAULT_COLOR)
            a.recycle()
        }
    }

    @Dimension fun getBorderWidth() : Int = borderWidth

    fun setBorderWidth(@Dimension dp: Int) {
        borderWidth = dp
    }

    fun getBorderColor():Int = color

    fun setBorderColor(hex: String) {
        color = parseInt(hex, 16)
    }

    fun setBorderColor(@ColorRes colorId: Int) {
        color = colorId
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val w = width
        val h = height

        var paint = Paint()
        val radius = w / 2f - borderWidth / 2f

        paint.setStyle(Paint.Style.STROKE)
        paint.setStrokeWidth(borderWidth.toFloat());
        paint.setColor(color)

        canvas!!.drawCircle( w / 2f, h / 2f, radius, paint)

    }
}