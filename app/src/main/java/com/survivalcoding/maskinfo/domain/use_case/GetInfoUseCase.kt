package com.survivalcoding.maskinfo.domain.use_case

import com.survivalcoding.maskinfo.data.data_source.dto.Mask
import com.survivalcoding.maskinfo.domain.model.Coordinate
import com.survivalcoding.maskinfo.domain.model.StoreInfo
import com.survivalcoding.maskinfo.domain.repository.InfoRepository

class GetInfoUseCase(private val infoRepository: InfoRepository) {
    suspend operator fun invoke(currentPage: Int = 0, userCoordinate: Coordinate?): List<StoreInfo?> =
        infoRepository.getMask(currentPage, userCoordinate)
}