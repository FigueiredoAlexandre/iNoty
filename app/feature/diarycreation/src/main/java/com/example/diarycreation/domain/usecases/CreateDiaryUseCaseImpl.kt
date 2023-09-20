package com.example.diarycreation.domain.usecases

import com.example.diarycreation.domain.model.DiaryItem
import com.example.diarycreation.domain.repository.DiaryCreationRepository
import kotlinx.coroutines.flow.Flow

internal class CreateDiaryUseCaseImpl(
    private val repository: DiaryCreationRepository
) : CreateDiaryUseCase {

    override fun saveDiary(diary: DiaryItem): Flow<Unit> =
        repository.saveDiary(diary)
}