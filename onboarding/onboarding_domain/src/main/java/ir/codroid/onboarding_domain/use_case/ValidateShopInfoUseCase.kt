package ir.codroid.onboarding_domain.use_case

import dagger.hilt.android.scopes.ViewModelScoped
import ir.codroid.core.R
import ir.codroid.core.util.UiText
import javax.inject.Inject

@ViewModelScoped
class ValidateShopInfoUseCase @Inject constructor() {

    operator fun invoke(
        shopName: String,
        shopDescription: String
    ): Result {
        if (shopName.isBlank()) {
            return Result.Failure(UiText.StringResource(R.string.err_shop_name_is_blank))
        }
        if (shopDescription.isBlank()) {
            return Result.Failure(UiText.StringResource(R.string.err_shop_description_is_blank))
        }
        return Result.Success
    }

    sealed class Result {
        data object Success : Result()

        data class Failure(val uiText: UiText) : Result()
    }
}