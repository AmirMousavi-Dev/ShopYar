package ir.codroid.util

import androidx.room.TypeConverter
import ir.codroid.entities.Unit

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