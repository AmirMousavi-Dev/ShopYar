package ir.codroid.merchandise_data.mapper

import ir.codroid.entities.Unit
import ir.codroid.merchandise_domain.model.CountUnit

fun Unit.toCountUnit(): CountUnit =
    when (this) {
        Unit.KG -> CountUnit.KG
        Unit.PACKAGE -> CountUnit.PACKAGE
        Unit.NUMBER -> CountUnit.NUMBER
    }


fun CountUnit.toUnit(): Unit =
    when (this) {
        CountUnit.KG -> Unit.KG
        CountUnit.PACKAGE -> Unit.PACKAGE
        CountUnit.NUMBER -> Unit.NUMBER
    }