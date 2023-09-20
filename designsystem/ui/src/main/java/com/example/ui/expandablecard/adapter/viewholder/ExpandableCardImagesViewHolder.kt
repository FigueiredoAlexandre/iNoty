package com.example.ui.expandablecard.adapter.viewholder

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.extensions.loadUri
import com.example.ui.databinding.InotyImageGalleryLayoutBinding

class ExpandableCardImagesViewHolder(
    private val binding: InotyImageGalleryLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        imageUri: Uri
    ) = with(binding) {
        galleryImageView.loadUri(imageUri)
    }

    companion object {

        fun build(parent: ViewGroup) = ExpandableCardImagesViewHolder(
            InotyImageGalleryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}