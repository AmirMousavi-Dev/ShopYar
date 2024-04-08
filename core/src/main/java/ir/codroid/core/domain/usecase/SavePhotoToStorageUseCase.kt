package ir.codroid.core.domain.usecase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.net.toUri

class SavePhotoToStorageUseCase(
    private val context: Context
) {


    operator fun invoke(fileName: String, uri: Uri): Uri? {
        return try {
            context.deleteFile(fileName)
            context.openFileOutput("$fileName.jpg", Context.MODE_PRIVATE).use { stream ->
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val source = ImageDecoder.createSource(context.contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)
                } else {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
                }
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                    throw Exception("Couldn't save bitmap")
                }
                val photos = context.filesDir.listFiles()?.filter {
                    it.canRead() && it.isFile && it.name.equals("$fileName.jpg")
                }?.map {
                    it.toUri()
                }?.get(0)
                return photos
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}