package com.survivalcoding.maskinfo.data.data_source.remote.mapper

import com.survivalcoding.maskinfo.domain.model.Photo
import com.survivalcoding.maskinfo.data.data_source.remote.dto.Hit

fun Hit.toPhoto() = Photo(
    id = id,
    url = webformatURL,
    description = tags,
)