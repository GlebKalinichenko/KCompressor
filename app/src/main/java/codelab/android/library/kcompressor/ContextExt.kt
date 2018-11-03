package codelab.android.library.kcompressor

import android.content.Context
import android.provider.MediaStore

fun Context.fetchLastGalleryImages(count: Int): MutableList<String> {
    var paths = mutableListOf<String>()

    val projection = arrayOf(
        MediaStore.Images.ImageColumns._ID,
        MediaStore.Images.ImageColumns.DATA,
        MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
        MediaStore.Images.ImageColumns.DATE_TAKEN,
        MediaStore.Images.ImageColumns.MIME_TYPE
    )

    val cursor = contentResolver
        .query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
            null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")

    var i = 0
    cursor.moveToFirst()
    while (!cursor.isAfterLast && i < count) {
        paths.add(cursor.getString(1))
        cursor.moveToNext()
        i++
    }

    cursor.close()

    return paths
}