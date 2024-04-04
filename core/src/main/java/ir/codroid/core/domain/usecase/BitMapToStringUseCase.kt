package ir.codroid.core.domain.usecase

import android.graphics.Bitmap
import android.os.Build
import android.util.Base64
import java.io.ByteArrayOutputStream

class BitMapToStringUseCase {
    operator fun invoke(bitmap: Bitmap?): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            bitmap?.compress(Bitmap.CompressFormat.WEBP_LOSSY, 16, byteArrayOutputStream)
        } else {
            bitmap?.compress(Bitmap.CompressFormat.WEBP, 16, byteArrayOutputStream)

        }
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}