@file:Suppress("unused")

package org.apc.dailyimageqqbot

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.console.command.isConsole
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.utils.MiraiLogger
import org.apc.dailyimageqqbot.sender.sendAPOD
import org.apc.dailyimageqqbot.sender.sendBing

object PluginMain : KotlinPlugin(
    @OptIn(ConsoleExperimentalApi::class)
    JvmPluginDescription.loadFromResource()
) {
    override fun onEnable() {
        SendAPODCommand.register()
        SendBingCommand.register()
    }

    override fun onDisable() {
        SendAPODCommand.unregister()
        SendBingCommand.unregister()
    }
}

object SendAPODCommand: SimpleCommand(
    PluginMain,
    "sendAPOD",
    "SendDailyAPOD"
) {
    @Handler
    suspend fun CommandSender.handle1() {
        val logger = MiraiLogger.create("DailyImageBot")
        if (this.isConsole()||this.subject==null) {
            logger.warning("No Target")
        } else {
            sendAPOD(this.subject!!)
        }
    }
    @Handler
    suspend fun CommandSender.handle2(target: Group) {
        sendAPOD(target)
    }
}

object SendBingCommand: SimpleCommand(
    PluginMain,
    "sendBing",
    "SendDailyBing"
) {
    @Handler
    suspend fun CommandSender.handle1() {
        val logger = MiraiLogger.create("DailyImageBot")
        if (this.isConsole()||this.subject==null) {
            logger.warning("No Target")
        } else {
            sendBing(this.subject!!)
        }
    }
    @Handler
    suspend fun CommandSender.handle2(target: Group) {
        sendBing(target)
    }
}