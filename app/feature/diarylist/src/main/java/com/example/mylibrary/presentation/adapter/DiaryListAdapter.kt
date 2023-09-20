package com.example.mylibrary.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.domain.model.DiaryListItem
import com.example.mylibrary.presentation.adapter.diffutil.DiaryListDiffUtil
import com.example.mylibrary.presentation.adapter.viewholder.DiaryListContentViewHolder
import com.example.mylibrary.presentation.adapter.viewholder.DiaryListHeaderViewHolder

private const val HEADER_TYPE = 0
private const val CONTENT_TYPE = 1

internal class DiaryListAdapter(
    private val onDiaryShowImagesClicked: (Int) -> Unit
) : ListAdapter<DiaryListItem, RecyclerView.ViewHolder>(
    DiaryListDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            HEADER_TYPE -> DiaryListHeaderViewHolder.build(parent)
            else -> DiaryListContentViewHolder.build(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DiaryListHeaderViewHolder -> holder.onBind(getItem(position) as DiaryListItem.Header)
            is DiaryListContentViewHolder -> holder.apply {
                clearData()
                onBind(
                    item = getItem(position) as DiaryListItem.DiaryContent,
                    onDiaryShowImagesClicked = { diaryId -> onDiaryShowImagesClicked(diaryId) }
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DiaryListItem.DiaryContent -> CONTENT_TYPE
            else -> HEADER_TYPE
        }
    }

}