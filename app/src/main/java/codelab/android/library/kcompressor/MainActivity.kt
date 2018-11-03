package codelab.android.library.kcompressor

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import codelab.android.library.kcomprerss.compressImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = coroutineContext + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchSinglePhoto()
    }

    fun fetchSinglePhoto() {

        launch(Dispatchers.IO) {
            try {
                val photos = fetchLastGalleryImages(1)
                val photoFile = photos.first()

                val srcFile = File(photoFile)
                var dstFile = createImageFile()

                Log.d(javaClass.canonicalName, "Before compression file size = ${srcFile.length()}")

                dstFile = srcFile.compressImage(50, 50, Bitmap.CompressFormat.JPEG, 70, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After JPEG 50x50, 70 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(100, 100, Bitmap.CompressFormat.JPEG, 70, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After JPEG 100x100, 70 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(150, 150, Bitmap.CompressFormat.JPEG, 70, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After JPEG 150x150, 70 compression file size = ${dstFile.length()}")

                dstFile = srcFile.compressImage(50, 50, Bitmap.CompressFormat.JPEG, 90, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After JPEG 50x50, 90 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(100, 100, Bitmap.CompressFormat.JPEG, 90, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After JPEG 100x100, 90 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(150, 150, Bitmap.CompressFormat.JPEG, 90, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After JPEG 150x150, 90 compression file size = ${dstFile.length()}")

                dstFile = srcFile.compressImage(50, 50, Bitmap.CompressFormat.PNG, 70, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After PNG 50x50, 70 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(100, 100, Bitmap.CompressFormat.PNG, 70, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After PNG 100x100, 70 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(150, 150, Bitmap.CompressFormat.PNG, 70, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After PNG 150x150, 70 compression file size = ${dstFile.length()}")

                dstFile = srcFile.compressImage(50, 50, Bitmap.CompressFormat.PNG, 90, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After PNG 50x50, 90 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(100, 100, Bitmap.CompressFormat.PNG, 90, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After PNG 100x100, 90 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(150, 150, Bitmap.CompressFormat.PNG, 90, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After PNG 150x150, 90 compression file size = ${dstFile.length()}")

                dstFile = srcFile.compressImage(50, 50, Bitmap.CompressFormat.WEBP, 70, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After WEBP 50x50, 70 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(100, 100, Bitmap.CompressFormat.WEBP, 70, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After WEBP 100x100, 70 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(150, 150, Bitmap.CompressFormat.WEBP, 70, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After WEBP 150x150, 70 compression file size = ${dstFile.length()}")

                dstFile = srcFile.compressImage(50, 50, Bitmap.CompressFormat.WEBP, 90, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After WEBP 50x50, 90 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(100, 100, Bitmap.CompressFormat.WEBP, 90, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After WEBP 100x100, 90 compression file size = ${dstFile.length()}")
                dstFile = srcFile.compressImage(150, 150, Bitmap.CompressFormat.WEBP, 90, dstFile.absolutePath)
                Log.d(javaClass.canonicalName, "After WEBP 150x150, 90 compression file size = ${dstFile.length()}")
            }
            catch (e: Exception) { e.printStackTrace() }
        }
    }
}
