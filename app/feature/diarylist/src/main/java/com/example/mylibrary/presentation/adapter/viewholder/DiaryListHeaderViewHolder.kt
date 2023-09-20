package com.example.mylibrary.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.databinding.DiaryListHeaderLayoutBinding
import com.example.mylibrary.domain.model.DiaryListItem

internal class DiaryListHeaderViewHolder(
    private val binding: DiaryListHeaderLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: DiaryListItem.Header) = with(binding) {
        dayNumber.text = item.dayNumber
        dayWeekName.text = item.weekDay
        monthName.text = item.month
        yearNumber.text = item.year
    }

    companion object {

        fun build(parent: ViewGroup) = DiaryListHeaderViewHolder(
            DiaryListHeaderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}