# DailyImageQQBot

一个自动向QQ群推送[APOD](https://apod.nasa.gov/apod/astropix.html)和[Bing每日一图](https://cn.bing.com)的Bot。

支持独立运行和[mirai-console](https://github.com/mamoe/mirai-console)]两种运行方式

## 1. 独立运行

下载Releases中的`console-x.y-all.jar`并直接运行即可一次性发送图片，运行完毕后会自动退出。

## 2. mirai-console

下载Release中的`plugin-x.y.mirai.jar`，参考[Mirai用户手册](https://github.com/mamoe/mirai/blob/dev/docs/UserManual.md)配置运行。

在私聊或群聊中输入`/sendAPOD`即可获得今日APOD；

在控制台中输入`/sendAPOD [群号]`即可在对应群聊中获得今日APOD;

`/sendBing`与`/sendBing [群号]`同理。

Powered by [mirai](https://github.com/mamoe/mirai)