package com.example.diarycreation.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diarycreation.domain.model.DiaryCreationGalleryItem
import com.example.diarycreation.presentation.adapter.difftutils.DiaryCreationImagesDiffUtils
import com.example.diarycreation.presentation.adapter.viewholder.DiaryCreationAddImageViewHolder
import com.example.diarycreation.presentation.adapter.viewholder.DiaryCreationImageViewHolder

private const val ADD_IMAGE_TYPE = 0
private const val IMAGE_TYPE = 1

internal class DiaryCreationImagesAdapter(
    private val onImageClicked: (String) -> Unit,
    private val onAddImageClicked: () -> Unit
) : ListAdapter<DiaryCreationGalleryItem, RecyclerView.ViewHolder>(
    DiaryCreationImagesDiffUtils()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            IMAGE_TYPE -> DiaryCreationImageViewHolder.build(parent)
            else -> DiaryCreationAddImageViewHolder.build(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DiaryCreationImageViewHolder -> {
                holder.onBind(getItem(position) as DiaryCreationGalleryItem.Image) { imageReference ->
                    onImageClicked(imageReference)
                }
            }
            is DiaryCreationAddImageViewHolder -> {
                holder.onBind(getItem(position) as DiaryCreationGalleryItem.AddImage) {
                    onAddImageClicked()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DiaryCreationGalleryItem.Image -> IMAGE_TYPE
            else -> ADD_IMAGE_TYPE
        }
    }
}