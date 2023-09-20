package com.example.diarycreation.presentation.adapter.viewholder

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diarycreation.databinding.DiaryCreationImageItemLayoutBinding
import com.example.diarycreation.domain.model.DiaryCreationGalleryItem
import com.example.extensions.loadUri

internal class DiaryCreationImageViewHolder(
    private val binding: DiaryCreationImageItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        item: DiaryCreationGalleryItem.Image,
        onImageClicked: (String) -> Unit
    ) = with(binding) {
        diaryImage.loadUri(Uri.parse(item.imageReference))
        root.setOnClickListener {
            onImageClicked(item.imageReference)
        }
    }

    companion object {

        fun build(parent: ViewGroup) = DiaryCreationImageViewHolder(
            DiaryCreationImageItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}