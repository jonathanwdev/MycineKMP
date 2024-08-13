package presentation.components

import PlatformName
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import okio.FileSystem
import getPlatform
import org.succlz123.lib.imageloader.ImageAsyncImageUrl
import org.succlz123.lib.imageloader.core.ImageCallback

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageLoader(modifier: Modifier = Modifier, url: String) {
    val platform = getPlatform()
    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }

    when (platform.name) {
        PlatformName.ANDROID -> {
            AsyncImage(
                model = url,
                modifier = modifier,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        PlatformName.DESKTOP -> {
            ImageAsyncImageUrl(url,
                imageCallback = ImageCallback {
                    Image(
                        modifier = modifier,
                        painter = it,
                        contentScale = ContentScale.Crop,
                        contentDescription = "123"
                    )
                })
        }
    }

}

fun getAsyncImageLoader(context: PlatformContext) =
    coil3.ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
    }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
        newDiskCache()
    }.crossfade(true).logger(DebugLogger()).build()

fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(1024L * 1024 * 1024) // 512MB
        .build()
}