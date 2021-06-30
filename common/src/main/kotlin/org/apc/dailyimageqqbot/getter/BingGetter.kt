package org.apc.dailyimageqqbot.getter

import com.google.gson.Gson
import org.apc.dailyimageqqbot.data.APODJson
import org.apc.dailyimageqqbot.data.BingImageJson
import org.apc.dailyimageqqbot.data.BingJson
import java.io.File
import java.net.URL

const val BING_URL = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1"

object BingGetter: Getter {

    override fun get(): GetterData {
        val result = URL(BING_URL).readText()
        val bingJson: BingImageJson = Gson().fromJson(result, BingJson::class.java).images[0]
        val historyFolder = File("./history")
        if (!historyFolder.exists()) {
            historyFolder.mkdir()
        }
        val bingFile = File("./history/Bing_"+bingJson.enddate)
        bingFile.createNewFile()
        val url = "https://cn.bing.com"+bingJson.url
        bingFile.writeBytes(URL(url).readBytes())
        return GetterData(bingFile, MediaType.image,"今日Bing："+bingJson.copyright)
    }

}