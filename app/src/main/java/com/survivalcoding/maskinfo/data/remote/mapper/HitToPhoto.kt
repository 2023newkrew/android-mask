package com.survivalcoding.maskinfo.data.remote.mapper

import com.survivalcoding.maskinfo.data.model.Photo
import com.survivalcoding.maskinfo.data.remote.dto.Hit

fun Hit.toPhoto() = Photo(
    id = id,
    url = webformatURL,
    description = tags,
)