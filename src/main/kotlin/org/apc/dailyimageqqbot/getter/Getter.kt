package org.apc.dailyimageqqbot.getter

import java.io.File

interface Getter {

    fun get(): GetterData

}

class GetterData (
    val file: File?,
    val mediaType: MediaType,
    val text: String
    )

enum class MediaType {
    image,video
}