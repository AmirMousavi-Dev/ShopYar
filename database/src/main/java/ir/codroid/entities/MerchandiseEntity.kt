package ir.codroid.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MerchandiseEntity(
    val name: String,
    val purchasePrice: Int,
    val salesPrice: Int,
    val code: String,
    val count: Double,
    val image: String?,
    val unit: Unit,
    @PrimaryKey val id: Int? = null
)
