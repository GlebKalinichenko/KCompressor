package codelab.android.library.kcomprerss

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.File
import java.io.FileOutputStream

suspend fun File.compressImage(reqWidth: Int, reqHeight: Int, compressFormat: Bitmap.CompressFormat,
                       quaility: Int, destinationPath: String): File {

    var fileOutputStream: FileOutputStream? = null
    val file = File(destinationPath).parentFile
    if (!file.exists()) {
        file.mkdirs()
    }
    try {
        fileOutputStream = FileOutputStream(destinationPath)
        // write the compressed bitmap at the destination specified by destinationPath.
        decodeSampledBitmap(reqWidth, reqHeight).compress(compressFormat, quaility, fileOutputStream)

    } finally {
        if (fileOutputStream != null) {
            fileOutputStream!!.flush()
            fileOutputStream!!.close()
        }
    }

    return File(destinationPath)

}

fun File.decodeSampledBitmap(reqWidth: Int, reqHeight: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(absolutePath, options)

    // Calculate inSampleSize
    options.inSampleSize = calculateSampleImageSize(options, reqWidth, reqHeight)

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false

    var scaledBitmap = BitmapFactory.decodeFile(absolutePath, options)

    val exif = ExifInterface(absolutePath)
    val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0)
    val matrix = Matrix()

    when (orientation) {
        6 -> matrix.postRotate(90f)
        3 -> matrix.postRotate(180f)
        8 -> matrix.postRotate(270f)
    }

    scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.width, scaledBitmap.height, matrix, true)
    return scaledBitmap
}

fun calculateSampleImageSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
    val imgHeight = options.outHeight
    val imgWidth = options.outWidth
    var sampleSize = 1

    if (imgHeight > reqHeight || imgWidth > reqWidth) {
        val halfHeight = imgHeight / 2
        val halfWidth = imgWidth / 2

        while ((halfHeight / sampleSize) >= reqHeight && (halfWidth / sampleSize) >= reqWidth) {
            sampleSize *=  2
        }
    }

    return sampleSize
}