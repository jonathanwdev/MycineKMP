enum class PlatformName {
    DESKTOP,
    ANDROID
}

interface Platform {
    val name: PlatformName
}

expect fun getPlatform(): Platform
