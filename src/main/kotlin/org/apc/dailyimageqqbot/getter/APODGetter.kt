package org.apc.dailyimageqqbot.getter

import com.google.gson.Gson
import org.apc.dailyimageqqbot.data.APODJson
import java.net.URL
import java.io.File

const val APOD_URL = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"

object APODGetter: Getter {

    override fun get(): GetterData {
        val result = URL(APOD_URL).readText()
        val apodJson: APODJson = Gson().fromJson(result,APODJson::class.java)
        val historyFolder = File("./history")
        if (!historyFolder.exists()) {
            historyFolder.mkdir()
        }
        val apodFile = File("./history/APOD_"+apodJson.date)
        apodFile.createNewFile()
        val stringBuilder = StringBuilder()
        stringBuilder.append(apodJson.title)
        stringBuilder.append("\n\n")
        stringBuilder.append(apodJson.explanation)
        stringBuilder.append("\n\nCopyright: ")
        stringBuilder.append(apodJson.copyright)
        if (apodJson.media_type==MediaType.video) {
            stringBuilder.append("\n\n")
            stringBuilder.append(apodJson.url)
        }
        val text = stringBuilder.toString()
        return when (apodJson.media_type) {
            MediaType.image -> {
                val url = if (apodJson.hdurl.isNullOrBlank()) apodJson.url else apodJson.hdurl
                apodFile.writeBytes(URL(url).readBytes())
                GetterData(apodFile, MediaType.image,text)
            }
            MediaType.video -> {
                val url = apodJson.url
                apodFile.writeBytes(URL(url).readBytes())
                GetterData(apodFile, MediaType.video,text)
            }
        }
    }

}