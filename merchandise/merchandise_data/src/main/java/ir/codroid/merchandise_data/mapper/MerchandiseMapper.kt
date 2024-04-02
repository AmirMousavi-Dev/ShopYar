package ir.codroid.merchandise_data.mapper

import ir.codroid.core.domain.usecase.BitMapToStringUseCase
import ir.codroid.core.domain.usecase.StringToBitMapUseCase
import ir.codroid.entities.MerchandiseEntity
import ir.codroid.merchandise_domain.model.Merchandise

fun MerchandiseEntity.toMerchandise(stringToBitMapUseCase: StringToBitMapUseCase): Merchandise =
    Merchandise(
        name = name,
        code = code,
        image = stringToBitMapUseCase(image),
        count = count,
        purchasePrice = purchasePrice,
        salesPrice = salesPrice,
        id = id,
        countUnit = unit.toCountUnit()
    )


fun Merchandise.toMerchandiseEntity(bitMapToStringUseCase: BitMapToStringUseCase): MerchandiseEntity =
    MerchandiseEntity(
        name = name,
        code = code,
        image = bitMapToStringUseCase(image),
        count = count,
        purchasePrice = purchasePrice,
        salesPrice = salesPrice,
        id = id,
        unit = countUnit.toUnit()
    )
