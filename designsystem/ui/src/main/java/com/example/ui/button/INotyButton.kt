package com.example.ui.button

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.example.ui.R
import com.example.ui.databinding.InotyButtonLayoutBinding

private const val BACKGROUND_DEFAULT_VALUE = 0

class INotyButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(ContextThemeWrapper(context, defStyleAttr), attributeSet, defStyleAttr) {

    private val binding: InotyButtonLayoutBinding = InotyButtonLayoutBinding
        .inflate(
            LayoutInflater.from(context),
            this, true
        )

    private var buttonText: String? = null
    private var buttonTextColor: ColorStateList? = null
    private var buttonBackground: Int? = null

    init {
        parseAttributes(context, attributeSet)
        setUpLayout()
    }

    private fun parseAttributes(context: Context, attributeSet: AttributeSet?) {
        context.withStyledAttributes(attributeSet, R.styleable.INotyButton) {
            setTextAttributes(
                R.styleable.INotyButton_btnText,
                R.styleable.INotyButton_btnTextColor
            )
            setBackground(R.styleable.INotyButton_btnBackground)
        }
    }

    private fun setUpLayout() = with(binding) {
        buttonBackground?.let { root.setBackgroundResource(it) }
        buttonTextColor?.let { iNotyButtonLabel.setTextColor(it) }
        buttonText?.let { iNotyButtonLabel.text = it }
    }

    private fun TypedArray.setTextAttributes(
        textReference: Int,
        textColorReference: Int
    ) {
        buttonText = this.getString(textReference)
        buttonTextColor = this.getColorStateList(textColorReference)
    }

    private fun TypedArray.setBackground(
        backgroundReference: Int
    ) {
        buttonBackground = this.getResourceId(backgroundReference, BACKGROUND_DEFAULT_VALUE)
    }
}