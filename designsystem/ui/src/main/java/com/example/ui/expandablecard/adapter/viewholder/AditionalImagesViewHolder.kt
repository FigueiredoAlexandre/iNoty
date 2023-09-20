package com.example.ui.expandablecard.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import com.example.ui.databinding.InotyAditionalImageLayoutBinding

class AditionalImagesViewHolder(
    private val binding: InotyAditionalImageLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        amountOfAditionalImages: Int
    ) = with(binding) {
        extraImagesView.text = root.context.getString(
            R.string.inoty_amount_of_aditional_images,
            amountOfAditionalImages
        )
    }

    companion object {

        fun build(parent: ViewGroup) = AditionalImagesViewHolder(
            InotyAditionalImageLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}