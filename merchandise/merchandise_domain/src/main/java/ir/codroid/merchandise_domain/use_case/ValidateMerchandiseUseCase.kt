package ir.codroid.merchandise_domain.use_case

import ir.codroid.core.R
import ir.codroid.core.util.UiText
import ir.codroid.merchandise_domain.model.Merchandise

class ValidateMerchandiseUseCase {

    operator fun invoke(
        merchandise: Merchandise
    ): Result {
        if (merchandise.name.isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_name_is_blank))

        if (merchandise.purchasePrice < 0 || merchandise.purchasePrice.toString().isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_purchase_price_is_blank))

        if (merchandise.salesPrice < 0 || merchandise.salesPrice.toString().isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_sales_price_is_blank))

        if (merchandise.code.isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_code_is_blank))

        if (merchandise.count < 0.0 || merchandise.count.toString().isBlank())
            return Result.Failure(UiText.StringResource(R.string.err_merchandise_count_is_blank))

        return Result.Success


    }

    sealed class Result {
        data object Success : Result()
        data class Failure(val uiText: UiText) : Result()
    }
}