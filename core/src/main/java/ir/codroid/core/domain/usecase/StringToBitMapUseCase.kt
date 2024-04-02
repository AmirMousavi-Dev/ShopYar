package ir.codroid.core.domain.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class StringToBitMapUseCase {
    operator fun invoke(string: String?): Bitmap? {
        val bitmap = string?.toByteArray()
        val decodedBytes = Base64.decode(bitmap, Base64.DEFAULT)
       return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}