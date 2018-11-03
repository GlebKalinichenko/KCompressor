package codelab.android.library.kcompressor

import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val storageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "/Camera/")
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        storageDir
    )

    return image
}