package ir.codroid.merchandise_domain.model

import android.graphics.Bitmap

data class Merchandise(
    val id: Int? = null,
    val name: String,
    val purchasePrice: Int,
    val salesPrice: Int,
    val code: String,
    val countUnit: CountUnit,
    val image: Bitmap?,
    val count: Double,
    )
