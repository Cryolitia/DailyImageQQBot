package org.apc.dailyimageqqbot.data

import org.apc.dailyimageqqbot.getter.MediaType

data class APODJson(
    val copyright: String? = null,
    val date: String,
    val explanation: String,
    val hdurl: String? = null,
    val media_type: MediaType,
    val title: String,
    val url: String
)