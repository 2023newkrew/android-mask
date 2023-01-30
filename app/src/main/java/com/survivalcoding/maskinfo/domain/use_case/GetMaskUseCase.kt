package com.survivalcoding.maskinfo.domain.use_case

import android.provider.ContactsContract
import com.survivalcoding.maskinfo.data.dto.Mask
import com.survivalcoding.maskinfo.domain.repository.InfoRepository
import kotlinx.coroutines.flow.Flow

class GetMaskUseCase(private val repository: InfoRepository) {
    suspend operator fun invoke(currentPage: Int): Mask = repository.getMask(currentPage)
}