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

    suspend fun send(contact: Contact, getterData: GetterData) {
        if (getterData.mediaType == MediaType.image) {
            if (getterData.text.length<=50) {
                val chain = buildMessageChain {
                    +contact.uploadImage(getterData.file!!)
                    +PlainText(getterData.text)
                }
                contact.sendMessage(chain)
            } else {
                contact.sendImage(getterData.file!!)
                contact.sendMessage(getterData.text)
            }
        } else {
            contact.sendMessage(getterData.text)
        }
    }

}

suspend fun sendAPOD(contact: Contact) {
    Sender.send(contact, APODGetter.get())
}

suspend fun sendBing(contact: Contact) {
    Sender.send(contact, BingGetter.get())
}