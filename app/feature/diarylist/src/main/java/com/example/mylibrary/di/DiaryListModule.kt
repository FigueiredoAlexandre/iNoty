package com.example.mylibrary.di

import com.example.mylibrary.data.datasource.DiaryListDataSourceImpl
import com.example.mylibrary.data.mapper.DiaryListMapper
import com.example.mylibrary.data.repository.DiaryListRepositoryImpl
import com.example.mylibrary.domain.usecase.GetListOfDiariesUseCaseImpl
import com.example.mylibrary.navigation.DiaryListNavigationImpl
import com.example.mylibrary.presentation.viewmodel.DiaryListViewModel
import com.example.navigation.featurenavigations.diarylist.DiaryListNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diaryListNavigationModule = module {
    factory<DiaryListNavigation> { DiaryListNavigationImpl() }
}

val diaryListModule = module {
    viewModel {
        DiaryListViewModel(
            getDiariesUseCase = GetListOfDiariesUseCaseImpl(
                repository = DiaryListRepositoryImpl(
                    dataSource = DiaryListDataSourceImpl(diaryDao = get()),
                    mapper = DiaryListMapper()
                )
            )
        )
    }
}