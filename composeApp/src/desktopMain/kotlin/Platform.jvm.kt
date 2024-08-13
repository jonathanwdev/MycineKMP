class JVMPlatform : Platform {
    override val name: PlatformName = PlatformName.DESKTOP
}

actual fun getPlatform(): Platform = JVMPlatform()

