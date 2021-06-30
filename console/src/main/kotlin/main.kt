import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.utils.BotConfiguration
import net.mamoe.mirai.utils.DeviceInfo.Companion.loadAsDeviceInfo
import org.apc.dailyimageqqbot.data.LoginConfig
import org.apc.dailyimageqqbot.data.SendWhich
import org.apc.dailyimageqqbot.sender.sendAPOD
import org.apc.dailyimageqqbot.sender.sendBing
import java.io.File
import java.security.MessageDigest
import java.util.*
import kotlin.system.exitProcess

fun main(args: Array<String>): Unit = runBlocking {
    val loginConfig = login()
    val bot = BotFactory.newBot(loginConfig.qq,loginConfig.password) {
        protocol = BotConfiguration.MiraiProtocol.ANDROID_PHONE
        deviceInfo = {
            File("./device.json").loadAsDeviceInfo()
        }
    }.alsoLogin()
    val targetGroup: Group = bot.getGroup(loginConfig.targetGroup)!!
    //targetGroup.sendMessage("hello,world!")
    delay(10000)
    when(loginConfig.sendWhich) {
        SendWhich.APOD -> {
            sendAPOD(targetGroup)
        }
        SendWhich.Bing -> {
            sendBing(targetGroup)
        }
        SendWhich.ALL -> {
            sendAPOD(targetGroup)
            delay(10000)
            sendBing(targetGroup)
        }
    }
    delay(10000)
    exitProcess(0)
}


private fun login(): LoginConfig {
    val loginConfigFile = File("Config.json")
    if (loginConfigFile.exists()) {
        try {
            return Gson().fromJson(loginConfigFile.readText(), LoginConfig::class.java)
        } catch (e:Exception) {
            e.printStackTrace()
            loginConfigFile.delete()
        }
    }
    val scanner = Scanner(System.`in`)
    print("Please input QQ number: ")
    val qq: Long = scanner.nextLong()
    print("Please input QQ password: ")
    val password: String = scanner.next()
    val md5: MessageDigest = MessageDigest.getInstance("MD5")
    md5.update(password.toByteArray())
    val passWordMD5 = md5.digest()
    print("Please input target group: ")
    val target: Long = scanner.nextLong()
    scanner.nextLine()
    val sendWhich: SendWhich
    while (true) {
        print("Please input which kinds of image you would like to send:\n[A]:APOD/[B]:Bing/[All]:All (Default All): ")
        val sendWhichString: String? = scanner.nextLine()
        if (sendWhichString.isNullOrBlank()) {
            sendWhich = SendWhich.ALL
            break
        }
        when (sendWhichString) {
            "A","a" -> {
                sendWhich = SendWhich.APOD
                break
            }
            "B","b" -> {
                sendWhich = SendWhich.Bing
                break
            }
            "All","all" -> {
                sendWhich = SendWhich.ALL
                break
            }
        }
    }
    val loginConfig = LoginConfig(qq,passWordMD5, target, sendWhich)
    while (true) {
        print("Would you like to save configs in Config.json? [Y/N] (Default N): ")
        val whetherSave: String? = scanner.nextLine()
        if (whetherSave.isNullOrBlank()) {
            return loginConfig
        }
        when (whetherSave) {
            "Y", "y" -> {
                try {
                    val config = GsonBuilder().setPrettyPrinting().create().toJson(loginConfig)
                    loginConfigFile.writeText(config)
                } catch (e:Exception) {
                    e.printStackTrace()
                }
                return loginConfig
            }
            "N", "n" -> {
                return loginConfig
            }
        }
    }
}