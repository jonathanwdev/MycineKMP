import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

class AndroidPlatform : Platform {
    override val name: PlatformName = PlatformName.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()

