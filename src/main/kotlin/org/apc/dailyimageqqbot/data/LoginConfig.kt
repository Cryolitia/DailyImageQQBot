package org.apc.dailyimageqqbot.data

data class LoginConfig(
    val qq: Long,
    val password: String,
    val targetGroup: Long,
    val sendWhich: SendWhich
)

enum class SendWhich {
    Bing, APOD, ALL
}