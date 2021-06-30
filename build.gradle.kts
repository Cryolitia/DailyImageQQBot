group = "org.apc.dailyimageqqbot"
version = "1.0"

ext {
    val miraiVersion = "2.6.7"
}

allprojects {
    repositories {
        maven {
            setUrl("https://maven.aliyun.com/nexus/content/groups/public/")
        }
        mavenCentral()
    }
}