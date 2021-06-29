package org.apc.dailyimageqqbot.data

data class LoginConfig(
    val qq: Long,
    val password: ByteArray,
    val targetGroup: Long,
    val sendWhich: SendWhich
)

enum class SendWhich {
    Bing, APOD, ALL
}