package org.apc.dailyimageqqbot.data

class BingJson {
    lateinit var images: Array<BingImageJson>
}

data class BingImageJson(
    val enddate: String,
    val url: String,
    val copyright: String,
)
