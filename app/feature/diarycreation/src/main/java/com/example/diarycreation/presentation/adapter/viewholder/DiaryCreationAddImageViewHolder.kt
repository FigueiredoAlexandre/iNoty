package com.example.diarycreation.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diarycreation.databinding.DiaryCreationAddImageItemLayoutBinding
import com.example.diarycreation.domain.model.DiaryCreationGalleryItem

internal class DiaryCreationAddImageViewHolder(
    private val binding: DiaryCreationAddImageItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        item: DiaryCreationGalleryItem.AddImage,
        onAddImageClicked: () -> Unit
    ) = with(binding.root) {
        setOnClickListener {
            onAddImageClicked()
        }
    }

    companion object {

        fun build(parent: ViewGroup) = DiaryCreationAddImageViewHolder(
            DiaryCreationAddImageItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}