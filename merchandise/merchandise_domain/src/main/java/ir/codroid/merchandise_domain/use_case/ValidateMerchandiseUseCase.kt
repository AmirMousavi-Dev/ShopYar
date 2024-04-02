package ir.codroid.merchandise_domain.use_case

import android.graphics.Bitmap
import ir.codroid.core.R
import ir.codroid.core.util.UiText
import ir.codroid.merchandise_domain.model.CountUnit
import ir.codroid.merchandise_domain.model.Merchandise

class ValidateMerchandiseUseCase  {

    operator fun invoke(
        name: String,
        purchasePrice: Int?,
        salesPrice: Int?,
        code: String,
        countUnit: CountUnit,
        image: Bitmap?,
        count: Double?,
    ): Result {
        if (name.isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_name_is_blank))

        if (purchasePrice == null || purchasePrice.toString().isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_purchase_price_is_blank))

        if (salesPrice == null || salesPrice.toString().isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_sales_price_is_blank))

        if (code.isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_code_is_blank))

        if (count == null || count.toString().isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_count_is_blank))

        val merchandise = Merchandise(
            name = name,
            purchasePrice = purchasePrice,
            salesPrice = salesPrice,
            code = code,
            countUnit = countUnit,
            image = image,
            count = count,
        )
        return Result.Success(merchandise)


    }

    sealed class Result {
        data class Success(val merchandise: Merchandise) : Result()
        data class Failure(val uiText: UiText) : Result()
    }
}