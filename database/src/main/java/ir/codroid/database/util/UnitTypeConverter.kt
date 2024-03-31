package ir.codroid.database.util

import androidx.room.TypeConverter
import ir.codroid.database.entities.Unit

class UnitTypeConverter {
    @TypeConverter
    fun toPriceUnit(value: String): Unit {
        return Unit.from(value)
    }

    @TypeConverter
    fun fromPriceUnit(value: Unit): String {
        return value.rawValue
    }
}