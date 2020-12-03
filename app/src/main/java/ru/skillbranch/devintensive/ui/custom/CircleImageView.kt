package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
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
        private const val DEFAULT_DIMENSION = 2
        private const val DEFAULT_COLOR = 0
    }

    private var borderWidth: Int = DEFAULT_DIMENSION
    private var color: Int = DEFAULT_COLOR

    init{
        if(attrs!= null){
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderWidth = a.getInt(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_DIMENSION)
            color= a.getInt(R.styleable.CircleImageView_cv_borderColor, DEFAULT_COLOR)
            a.recycle()
        }
    }

    @Dimension fun getBorderWidth() : Int = borderWidth

    fun setBorderWidth(@Dimension dp:Int) {
        borderWidth = dp
    }

    fun getBorderColor():Int = color

    fun setBorderColor(hex:String) {
        color = parseInt("ED05265A", 16)
    }

    fun setBorderColor(@ColorRes colorId: Int) {
        color = colorId
    }

}