package com.example.mylibrary.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.common.presentation.extensions.onAction
import com.example.common.presentation.extensions.onStateChanged
import com.example.common.presentation.extensions.stopRefreshing
import com.example.mylibrary.databinding.DiaryListActivityBinding
import com.example.mylibrary.presentation.adapter.DiaryListAdapter
import com.example.mylibrary.presentation.viewmodel.DiaryListViewModel
import com.example.mylibrary.presentation.viewmodel.action.DiaryListAction
import com.example.mylibrary.presentation.viewmodel.state.DiaryListState
import com.example.navigation.featurenavigations.navigationrouter.INotyRouter
import org.koin.android.ext.android.inject

internal class DiaryListActivity : AppCompatActivity() {

    private lateinit var binding: DiaryListActivityBinding
    private val diaryListViewModel: DiaryListViewModel by inject()
    private val router: INotyRouter by inject()
    private val diaryListAdapter: DiaryListAdapter by lazy {
        DiaryListAdapter(
            onDiaryShowImagesClicked = { diaryId ->
                diaryListViewModel.onDiaryShowImagesClicked(diaryId)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiaryListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setListeners()
        onStateChanged(diaryListViewModel, ::handleState)
        onAction(diaryListViewModel, ::handleAction)
    }

    override fun onResume() {
        super.onResume()
        diaryListViewModel.onDiaryListResumed()
    }

    private fun setUpRecyclerView() = with(binding.diariesRecyclerView) {
        adapter = diaryListAdapter
    }

    private fun setListeners() = with(binding) {
        writeNewDiaryButton.setOnClickListener {
            diaryListViewModel.onWriteNewDiaryButtonClicked()
        }
        refreshLayout.setOnRefreshListener {
            diaryListViewModel.onScreenRefreshed()
        }
    }

    private fun handleState(state: DiaryListState) {
        diaryListAdapter.submitList(state.diaries)
    }

    private fun handleAction(action: DiaryListAction) {
        when (action) {
            DiaryListAction.NavigateToDiaryCreation -> router.toDiaryCreation(this)
            DiaryListAction.EndRefreshing -> endRefresh()
            else -> onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun endRefresh() {
        binding.refreshLayout.stopRefreshing()
    }

    companion object {

        fun newInstance(context: Context) =
            Intent(context, DiaryListActivity::class.java).also {
                context.startActivity(it)
            }
    }
}