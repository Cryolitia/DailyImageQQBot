package org.apc.dailyimageqqbot.sender

import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.contact.Contact.Companion.uploadImage
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.RemoteFile.Companion.sendFile
import org.apc.dailyimageqqbot.getter.APODGetter
import org.apc.dailyimageqqbot.getter.BingGetter
import org.apc.dailyimageqqbot.getter.GetterData
import org.apc.dailyimageqqbot.getter.MediaType

object Sender {

    suspend fun send(group: Group, getterData: GetterData) {
        if (getterData.mediaType == MediaType.image) {
            if (getterData.text.length<=50) {
                val chain = buildMessageChain {
                    +group.uploadImage(getterData.file)
                    +PlainText(getterData.text)
                }
                group.sendMessage(chain)
            } else {
                group.sendImage(getterData.file)
                group.sendMessage(getterData.text)
            }
        } else {
            group.sendMessage(getterData.text)
            group.sendFile("/",getterData.file)
        }
    }

}

suspend fun sendAPOD(group: Group) {
    Sender.send(group, APODGetter.get())
}

suspend fun sendBing(group: Group) {
    Sender.send(group, BingGetter.get())
}