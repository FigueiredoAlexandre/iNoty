package com.example.diarylist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.diarylist.stubs.mockDiariesListWithExpandedState
import com.example.diarylist.stubs.mockedListOfDiaries
import com.example.mylibrary.domain.usecase.GetListOfDiariesUseCase
import com.example.mylibrary.presentation.util.DiaryListHelper
import com.example.mylibrary.presentation.viewmodel.DiaryListViewModel
import com.example.mylibrary.presentation.viewmodel.action.DiaryListAction
import com.example.mylibrary.presentation.viewmodel.state.DiaryListState
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class DiaryListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var viewModel: DiaryListViewModel
    private val getDiariesUseCase = mockk<GetListOfDiariesUseCase>()
    private val helper = DiaryListHelper()
    private val dispatcher = UnconfinedTestDispatcher()
    private val stateObserver = mockk<Observer<DiaryListState>>(relaxed = true)
    private val actionObserver = mockk<Observer<DiaryListAction>>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        setUpViewModel()
    }

    @Test
    fun `onDiaryListResumed Should return list of diaries When success`() {
        // Given
        val diariesList = mockedListOfDiaries()
        val expectedLoadingState = DiaryListState(isLoading = false)
        val expectedFinalState = expectedLoadingState.copy(isLoading = false, diaries = diariesList)
        every { getDiariesUseCase.getDiaries() } returns flowOf(diariesList)

        // When
        viewModel.onDiaryListResumed()

        // Then
        verifyOrder {
            stateObserver.onChanged(expectedLoadingState)
            stateObserver.onChanged(expectedFinalState)
        }
    }

    @Test
    fun `onDiaryShowImagesClicked Should return updated list of diaries with one expanded When called`() {
        // Given
        val diariesList = mockDiariesListWithExpandedState()
        val expectedFinalState = DiaryListState(diaries = diariesList)
        helper.diaries = diariesList

        // When
        viewModel.onDiaryShowImagesClicked(1)

        // Then
        verify {
            stateObserver.onChanged(expectedFinalState)
        }
    }

    @Test
    fun `onWriteNewDiaryButtonClicked Should start navigation to diary creation When called`() {
        // Given
        val expectedAction = DiaryListAction.NavigateToDiaryCreation

        // When
        viewModel.onWriteNewDiaryButtonClicked()

        // Then
        verify {
            actionObserver.onChanged(expectedAction)
        }
    }

    @Test
    fun `onScreenRefreshed Should end swipe layout refresh When called`() {
        // Given
        val expectedAction = DiaryListAction.EndRefreshing

        // When
        viewModel.onScreenRefreshed()

        // Then
        verify {
            actionObserver.onChanged(expectedAction)
        }
    }

    private fun setUpViewModel() {
        viewModel = DiaryListViewModel(
            getDiariesUseCase = getDiariesUseCase,
            helper = helper,
            dispatcher = dispatcher
        )
        viewModel.state.observeForever(stateObserver)
        viewModel.action.observeForever(actionObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}