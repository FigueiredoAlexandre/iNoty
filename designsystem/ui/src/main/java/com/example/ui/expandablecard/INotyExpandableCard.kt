package com.example.ui.expandablecard

import android.content.Context
import android.net.Uri
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.BounceInterpolator
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.example.extensions.gone
import com.example.extensions.visible
import com.example.ui.databinding.InotyExpandableCardLayoutBinding
import com.example.ui.expandablecard.adapter.ExpandableCardImagesAdapter
import com.example.ui.expandablecard.adapter.model.GalleryItem

private const val EXPAND_ANIMATION_DURATION = 750L
private const val SHOW_IMAGES = "Mostrar imagens"
private const val HIDE_IMAGES = "Ocultar imagens"
private const val TWO_IMAGES = 2
private const val INDEX_FIRST_POSITION = 0
private const val INDEX_OF_SECOND_ELEMENT = 2

class INotyExpandableCard @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defAttrStyle: Int = 0
) : CardView(context, attributeSet, defAttrStyle) {

    private val binding: InotyExpandableCardLayoutBinding = InotyExpandableCardLayoutBinding
        .inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    private var onShowImagesClick: () -> Unit = {}
    private var isExpanded: Boolean = false
    private val imagesList: MutableList<GalleryItem> = mutableListOf()
    private val imagesAdapter: ExpandableCardImagesAdapter by lazy {
        ExpandableCardImagesAdapter()
    }

    init {
        updateShowImagesTextVisibility()
        setListeners()
    }

    private fun setUpImagesRecyclerView() = with(binding.imagesRecyclerView) {
        adapter = imagesAdapter
    }

    private fun setListeners() = with(binding) {
        showImagesText.setOnClickListener {
            onShowImagesClick()
            startExpandingAnimation()
        }
    }

    private fun startExpandingAnimation() {
        isExpanded = !isExpanded
        updateImagesText()
        if (isExpanded) {
            expandBodyToShowImages()
        } else {
            shrinkBodyToHideImages()
        }
    }

    private fun updateImagesText() {
        binding.showImagesText.text = getShowImageText()
    }

    private fun getShowImageText(): String {
        return if (isExpanded) {
            HIDE_IMAGES
        } else {
            SHOW_IMAGES
        }
    }

    private fun expandBodyToShowImages() {
        TransitionManager.beginDelayedTransition(
            this,
            expandAnimation()
        )
        binding.galleryContainer.visible()
    }

    private fun shrinkBodyToHideImages() {
        binding.galleryContainer.gone()
    }

    private fun expandAnimation() = ChangeBounds().also {
        it.duration = EXPAND_ANIMATION_DURATION
        it.interpolator = BounceInterpolator()
    }

    fun setEmotionEmoji(
        @DrawableRes emojiRes: Int
    ) {
        binding.emojiImageView.setImageResource(emojiRes)
    }

    fun setEmotionLabel(
        @StringRes label: Int
    ) {
        binding.emotionalStateTextView.text = context.getString(label)
    }

    fun setEmotionColor(@ColorRes color: Int) {
        binding.emotionAndTimeContainer.setBackgroundResource(color)
    }

    fun setText(@StringRes textRes: Int) {
        binding.diaryText.text = context.getString(textRes)
    }

    fun setText(text: String) {
        binding.diaryText.text = text
    }

    fun refreshLayout() = with(binding) {
        showImagesText.text = SHOW_IMAGES
        showImagesText.gone()
        isExpanded = false
        galleryContainer.gone()
        imagesList.clear()
        imagesAdapter.submitList(imagesList)
    }

    fun shouldBeExpanded(isExpanded: Boolean) {
        this.isExpanded = isExpanded
        updateImagesText()
        binding.galleryContainer.isVisible = isExpanded
    }

    fun onShowImagesClicked(callback: () -> Unit) {
        onShowImagesClick = callback
    }

    fun fetchImages(imagesUris: List<Uri>) {
        setUpImagesRecyclerView()
        if (imagesUris.size > TWO_IMAGES) {
            imagesUris.subList(
                INDEX_FIRST_POSITION,
                INDEX_OF_SECOND_ELEMENT
            ).forEach {
                imagesList.add(
                    GalleryItem.ImageItem(it)
                )
            }
            imagesList.add(
                GalleryItem.MoreImagesItem(imagesUris.size - TWO_IMAGES)
            )
        } else {
            imagesUris.forEach { uri ->
                imagesList.add(GalleryItem.ImageItem(uri))
            }
        }
        imagesAdapter.submitList(imagesList)
        updateShowImagesTextVisibility()
    }

    private fun updateShowImagesTextVisibility() {
        binding.showImagesText.isVisible = imagesList.isNotEmpty()
    }
}