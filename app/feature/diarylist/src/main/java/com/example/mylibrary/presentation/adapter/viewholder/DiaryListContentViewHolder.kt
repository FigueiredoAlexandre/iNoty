package com.example.mylibrary.presentation.adapter.viewholder

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.databinding.DiaryListDiaryItemLayoutBinding
import com.example.mylibrary.domain.model.DiaryListItem
import com.example.util.emotions.getEmojiDescription
import com.example.util.emotions.getEmotionColor

internal class DiaryListContentViewHolder(
    private val binding: DiaryListDiaryItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun clearData() = with(binding.INotyExpandableCard) {
        refreshLayout()
    }

    fun onBind(
        item: DiaryListItem.DiaryContent,
        onDiaryShowImagesClicked: (Int) -> Unit
    ) = with(binding.INotyExpandableCard) {
        setEmotionEmoji(item.emojiReference)
        setText(item.body)
        shouldBeExpanded(item.isExpanded)
        handleItemList(item.images)
        setEmotionLabel(item.emojiReference.getEmojiDescription())
        setEmotionColor(item.emojiReference.getEmotionColor())
        onShowImagesClicked {
            onDiaryShowImagesClicked(item.id)
        }
    }

    private fun parseImagesUris(images: List<String>): List<Uri> {
        return images.map { imagesReference ->
            Uri.parse(imagesReference)
        }
    }

    private fun handleItemList(images: List<String>) = with(binding.INotyExpandableCard) {
        if (images.isEmpty()) {
            fetchImages(emptyList())
        } else {
            fetchImages(parseImagesUris(images))
        }
    }

    companion object {

        fun build(parent: ViewGroup) = DiaryListContentViewHolder(
            DiaryListDiaryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}