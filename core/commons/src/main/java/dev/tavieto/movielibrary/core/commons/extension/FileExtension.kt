package dev.tavieto.movielibrary.core.commons.extension

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun File.toMultipartBody(dataName: String): MultipartBody.Part {
    val requestFile: RequestBody = RequestBody.create(MediaType.parse("image/png"), this)
    return MultipartBody.Part.createFormData(dataName, name, requestFile)
}
