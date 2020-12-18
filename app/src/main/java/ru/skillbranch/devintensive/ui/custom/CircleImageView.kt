package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.repositories.PreferencesRepository
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
        private const val DEFAULT_COLOR = Color.WHITE
    }

    private var borderWidth: Int = DEFAULT_DIMENSION
    private var color: Int = DEFAULT_COLOR

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderWidth = a.getDimension(
                R.styleable.CircleImageView_cv_borderWidth,
                DEFAULT_DIMENSION.toFloat()
            ).toInt()
            color = a.getInt(R.styleable.CircleImageView_cv_borderColor, DEFAULT_COLOR)
            a.recycle()
        }
    }

    @Dimension
    fun getBorderWidth(): Int = borderWidth

    fun setBorderWidth(@Dimension dp: Int) {
        borderWidth = dp
    }

    fun getBorderColor(): Int = color

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

        val radius = w / 2f - borderWidth / 2f

        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = borderWidth.toFloat()
            color = this@CircleImageView.color
            canvas!!.drawCircle(w / 2f, h / 2f, radius, this)
        }

        val initials = PreferencesRepository.getProfile().firstName.trim().take(1)  +
                PreferencesRepository.getProfile().lastName.trim().take(1)

        if (!initials.isNullOrEmpty()) {

            Paint().apply {
                style = Paint.Style.FILL
                color = if(PreferencesRepository.getAppTheme() == AppCompatDelegate.MODE_NIGHT_NO) {
                    resources.getColor(R.color.color_accent, App.applicationContext().theme)
                } else {
                    resources.getColor(R.color.color_accent_night, App.applicationContext().theme)
                }
                canvas!!.drawCircle(w / 2f, h / 2f, radius, this)
            }

            val textPaint = Paint().apply {
                textAlign = Paint.Align.CENTER
                textSize = radius / 1.5f
                color = DEFAULT_COLOR
            }

            val xPos = w / 2
            //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.
            val yPos = (h / 2 - (textPaint.descent() + textPaint.ascent()) / 2).toInt()
            canvas!!.drawText(initials.toUpperCase(), xPos.toFloat(), yPos.toFloat(), textPaint)
        }

    }
}