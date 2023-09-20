package com.example.ui.progress

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.ui.R
import com.example.ui.databinding.InotyProgressDialogLayoutBinding

class INotyProgressDialog : DialogFragment() {

    private var _binding: InotyProgressDialogLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.iNotyProgressDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpWindow()
        _binding = InotyProgressDialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setUpWindow() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    companion object {

        fun newInstance() = INotyProgressDialog()
    }
}