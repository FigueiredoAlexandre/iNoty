package com.example.ui.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import com.example.ui.databinding.InotyToolbarLayoutBinding
import com.google.android.material.appbar.AppBarLayout

class INotyToolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : AppBarLayout(context, attributeSet, defStyleAttrs) {

    private val binding: InotyToolbarLayoutBinding = InotyToolbarLayoutBinding
        .inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    private var onMenuItemClickedCallback: (Int) -> Unit = {}
    private var onNavigationClickedCallback: () -> Unit = {}

    init {
        setListeners()
    }

    private fun setListeners() = with(binding.iNotyToolbar) {
        setOnMenuItemClickListener {
            onMenuItemClickedCallback(it.itemId)
            true
        }
        setNavigationOnClickListener {
            onNavigationClickedCallback.invoke()
        }
    }

    fun setNavigationIcon(
        @DrawableRes iconReference: Int
    ) {
        binding.iNotyToolbar.setNavigationIcon(iconReference)
    }

    fun inflateMenu(@MenuRes menuReference: Int) {
        binding.iNotyToolbar.inflateMenu(menuReference)
    }

    fun setTitle(@StringRes stringReference: Int) {
        binding.iNotyToolbar.title = context.getString(stringReference)
    }

    fun setSubtitle(@StringRes stringReference: Int) {
        binding.iNotyToolbar.subtitle = context.getString(stringReference)
    }

    fun onMenuItemClicked(block: (Int) -> Unit) {
        onMenuItemClickedCallback = block
    }

    fun onNavigationIconClicked(block: () -> Unit) {
        onNavigationClickedCallback = block
    }
}