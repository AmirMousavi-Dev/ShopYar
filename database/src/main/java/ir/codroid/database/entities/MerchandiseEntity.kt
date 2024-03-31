package ir.codroid.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["code"], unique = true)]
)
data class MerchandiseEntity(
    val name: String,
    val purchasePrice: Int,
    val salesPrice: Int,
    @ColumnInfo(name = "code")
    val code: String,
    val count: Double,
    val image: String,
    val unit: Unit,
    @PrimaryKey val id: Int? = null
)
