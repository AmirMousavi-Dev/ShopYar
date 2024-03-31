package ir.codroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.codroid.database.dao.MerchandiseDao
import ir.codroid.database.entities.MerchandiseEntity
import ir.codroid.database.util.UnitTypeConverter


@Database(
    entities = [MerchandiseEntity::class],
    version = 1
)
@TypeConverters(UnitTypeConverter::class)
abstract class ShopYarDatabase : RoomDatabase() {
    abstract val merchandiseDao: MerchandiseDao

    companion object {
        const val DATABASE_NAME = "shopYarDatabase"
    }
}