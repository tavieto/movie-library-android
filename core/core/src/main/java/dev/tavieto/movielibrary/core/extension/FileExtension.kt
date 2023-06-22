package dev.tavieto.movielibrary.core.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.File

fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
    outputStream().use { out ->
        bitmap.compress(format, quality, out)
        out.flush()
        out.close()
    }
}

fun File.writeBase64(base64: String, format: Bitmap.CompressFormat, quality: Int) {
    val imageBytes = Base64.decode(base64.replace("data:image/png;base64,", ""), Base64.DEFAULT)
    var bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    bitmap = Bitmap.createScaledBitmap(
        bitmap,
        bitmap.width.div(other = 3),
        bitmap.height.div(other = 3),
        false
    )

    this.writeBitmap(bitmap, format, quality)
}

fun File.readBitmap(): Bitmap {
    inputStream().use { input ->
        return BitmapFactory.decodeStream(input)
    }
}
