package ir.codroid.merchandise_data.mapper

import ir.codroid.database.entities.MerchandiseEntity
import ir.codroid.merchandise_domain.model.Merchandise

fun MerchandiseEntity.toMerchandise(): Merchandise =
    Merchandise(
        name = name,
        code = code,
        image = image,
        count = count,
        purchasePrice = purchasePrice,
        salesPrice = salesPrice,
        id = id,
        countUnit = unit.toCountUnit()
    )


fun Merchandise.toMerchandiseEntity(): MerchandiseEntity =
    MerchandiseEntity(
        name = name,
        code = code,
        image = image,
        count = count,
        purchasePrice = purchasePrice,
        salesPrice = salesPrice,
        id = id,
        unit = countUnit.toUnit()
    )
