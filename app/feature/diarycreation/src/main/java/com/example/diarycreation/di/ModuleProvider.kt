package com.example.diarycreation.di

import com.example.diarycreation.data.datasource.DiaryCreationDataSourceImpl
import com.example.diarycreation.data.mapper.DiaryCreationMapper
import com.example.diarycreation.data.repository.DiaryCreationRepositoryImpl
import com.example.diarycreation.domain.usecases.CreateDiaryUseCase
import com.example.diarycreation.domain.usecases.CreateDiaryUseCaseImpl
import com.example.diarycreation.presentation.helper.DiaryCreationGalleryHelper
import com.example.diarycreation.presentation.navigation.DiaryCreationNavigationImpl
import com.example.diarycreation.presentation.viewmodel.DiaryCreationViewModel
import com.example.navigation.featurenavigations.diarycreation.DiaryCreationNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diaryCreationNavigationModule = module {
    factory<DiaryCreationNavigation> { DiaryCreationNavigationImpl() }
}

val diaryCreationModule = module {
    viewModel {
        DiaryCreationViewModel(
            createDiaryUseCase = CreateDiaryUseCaseImpl(
                repository = DiaryCreationRepositoryImpl(
                    diaryCreationSource = DiaryCreationDataSourceImpl(
                        diaryDao = get()
                    ),
                    diaryCreationMapper = DiaryCreationMapper()
                )
            ),
            helper = DiaryCreationGalleryHelper()
        )
    }
}