package ir.codroid.core.data.preferences

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ir.codroid.core.domain.model.ShopInfo
import ir.codroid.core.domain.preferences.Preferences
import ir.codroid.core.util.Constants.DATASTORE_NAME
import kotlinx.coroutines.flow.first
import java.io.ByteArrayOutputStream
import javax.inject.Inject


private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
        by preferencesDataStore(name = DATASTORE_NAME)


class DatastorePreferences @Inject constructor(
    private val context: Context
) : Preferences {
    override suspend fun saveShopName(shopName: String) {
        val preferencesKey = stringPreferencesKey(SHOP_NAME_KEY)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = shopName
        }
    }

    override suspend fun saveShopDescription(shopDescription: String) {
        val preferencesKey = stringPreferencesKey(SHOP_DESCRIPTION_KEY)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = shopDescription
        }
    }

    override suspend fun saveShopImage(shopImage: Bitmap) {
        val preferencesKey = stringPreferencesKey(SHOP_IMAGE_KEY)
        context.dataStore.edit { preferences ->
            val byteArrayOutputStream = ByteArrayOutputStream()
            shopImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            Log.d("saveShopImage", "saveShopImage: ${Base64.encodeToString(byteArray, Base64.DEFAULT)}")
            preferences[preferencesKey] = Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
    }

    override suspend fun loadShopInfo(): ShopInfo? {
        return try {
            val shopNamePreferencesKey = stringPreferencesKey(SHOP_NAME_KEY)
            val shopDescriptionPreferencesKey = stringPreferencesKey(SHOP_DESCRIPTION_KEY)
            val shopImagePreferencesKey = stringPreferencesKey(SHOP_IMAGE_KEY)
            val preferences = context.dataStore.data.first()
            val shopName = preferences[shopNamePreferencesKey]
            val shopDescription = preferences[shopDescriptionPreferencesKey]
            val shopImageBitmap = preferences[shopImagePreferencesKey]?.toByteArray()
            val decodedBytes = Base64.decode(shopImageBitmap, Base64.DEFAULT)
            val shopImage = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

            ShopInfo(
                shopName = shopName,
                shopDescription = shopDescription,
                shopImage = shopImage
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val SHOP_NAME_KEY = "shopNameKey"
        private const val SHOP_DESCRIPTION_KEY = "shopDescriptionKey"
        private const val SHOP_IMAGE_KEY = "shopImageKey"
    }
}