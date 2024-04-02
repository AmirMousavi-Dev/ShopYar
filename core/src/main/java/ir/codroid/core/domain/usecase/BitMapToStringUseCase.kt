package ir.codroid.core.domain.usecase

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class BitMapToStringUseCase {
    operator fun invoke(bitmap: Bitmap?): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}