package com.example.diarycreation.presentation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.common.presentation.extensions.onAction
import com.example.common.presentation.extensions.onStateChanged
import com.example.common.presentation.media.PickUpImagesLauncher
import com.example.common.presentation.media.openGallery
import com.example.common.presentation.permissions.PermissionLauncher
import com.example.common.presentation.permissions.requestRuntimePermission
import com.example.diarycreation.databinding.DiaryCreationActivityBinding
import com.example.diarycreation.presentation.adapter.DiaryCreationImagesAdapter
import com.example.diarycreation.presentation.viewmodel.DiaryCreationViewModel
import com.example.diarycreation.presentation.viewmodel.action.DiaryCreationAction
import com.example.diarycreation.presentation.viewmodel.state.DiaryCreationState
import com.example.extensions.hideINotyProgressDialog
import com.example.extensions.showINotyProgressDialog
import com.example.ui.snackbar.INotySnackBar
import com.example.util.emotions.Emotion
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val SNACK_BAR_DURATION = 2000

internal class DiaryCreationActivity : AppCompatActivity() {

    private lateinit var binding: DiaryCreationActivityBinding
    private val diaryCreationViewModel: DiaryCreationViewModel by viewModel()
    private val diaryImagesAdapter: DiaryCreationImagesAdapter by lazy {
        DiaryCreationImagesAdapter(
            onImageClicked = { _ -> },
            onAddImageClicked = { diaryCreationViewModel.onAddImageClicked() }
        )
    }
    private val permissionLauncher: PermissionLauncher =
        requestRuntimePermission(Manifest.permission.READ_EXTERNAL_STORAGE) { permissionStatus ->
            diaryCreationViewModel.onPermissionVerified(permissionStatus)
        }

    private val pickUpImagesLauncher: PickUpImagesLauncher =
        openGallery { imageUri ->
            diaryCreationViewModel.onGalleryImageChosen(imageUri.toString())
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiaryCreationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        setUpRecyclerView()
        setUpToolbar()
        onAction(diaryCreationViewModel, ::handleAction)
        onStateChanged(diaryCreationViewModel, ::handleState)
    }

    private fun setListeners() = with(binding) {
        nextEmojiArrow.setOnClickListener {
            diaryCreationViewModel.onNextEmotionArrowClicked()
        }
        previousEmojiArrow.setOnClickListener {
            diaryCreationViewModel.onPreviousEmotionArrowClicked()
        }
        diaryTitleEditText.doOnTextChanged { title, _, _, _ ->
            diaryCreationViewModel.onTitleEntered(title.toString())
        }
        diaryBodyEditText.doOnTextChanged { content, _, _, _ ->
            diaryCreationViewModel.onDiaryContentEntered(content.toString())
        }
        saveDiaryButton.setOnClickListener {
            diaryCreationViewModel.onSaveDiaryButtonClicked()
        }
    }

    private fun setUpToolbar() = with(binding.diaryCreationToolbar) {
        onNavigationIconClicked {
            diaryCreationViewModel.onToolbarBackIconClicked()
        }
    }

    private fun handleAction(action: DiaryCreationAction) {
        when(action) {
            DiaryCreationAction.RequestMediaAccessPermission -> requestMediaStoragePermission()
            DiaryCreationAction.OpenGallery -> pickUpImagesLauncher.launchGallery()
            is DiaryCreationAction.ShowSaveSuccessSnackBar -> showSuccessSnackBar(action.message)
            else -> onBackPressed()
        }
    }

    private fun showSuccessSnackBar(message: String) {
        binding.root.let {
            INotySnackBar.make(it, message)
                .setDuration(SNACK_BAR_DURATION)
                .onSnackBarDismissed { diaryCreationViewModel.onSuccessSnackbarDismissed() }
                .show()

        }
    }

    private fun handleState(state: DiaryCreationState) {
        diaryImagesAdapter.submitList(state.diaryListOfImages)
        setUpEmotion(state.currentEmotion)
        setUpTitleHint(state.titleTextFieldHint)
        setUpContentHint(state.contentTextFieldHint)
        showLoading(state.isLoading)
        hideLoading(state.isLoading)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            showINotyProgressDialog()
        }
    }

    private fun hideLoading(isLoading: Boolean) {
        if (isLoading.not()) {
            hideINotyProgressDialog()
        }
    }

    private fun setUpRecyclerView() = with(binding.diaryCreationImagesRecyclerView) {
        adapter = diaryImagesAdapter
    }

    private fun requestMediaStoragePermission() {
        permissionLauncher.requestPermission()
    }

    private fun setUpEmotion(currentEmotion: Emotion) = with(binding) {
        emotionEmojiImageView.setImageResource(currentEmotion.emoji)
        diaryCreationToolbar.setTitle(currentEmotion.emotionName)
    }

    private fun setUpTitleHint(newHint: String) = with(binding.diaryTitleEditText) {
        hint = newHint
    }

    private fun setUpContentHint(newHint: String) = with(binding.diaryBodyEditText) {
        hint = newHint
    }

    companion object {

        fun newInstance(context: Context) =
            Intent(context, DiaryCreationActivity::class.java).also {
                context.startActivity(it)
            }
    }
}