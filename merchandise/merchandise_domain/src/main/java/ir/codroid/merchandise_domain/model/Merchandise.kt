package ir.codroid.merchandise_domain.model

data class Merchandise(
    val id: Int?,
    val name: String,
    val purchasePrice: Int,
    val salesPrice: Int,
    val code: String,
    val countUnit: CountUnit,
    val image: String,
    val count: Double,
    )
