package ir.codroid.merchandise_domain.use_case

data class MerchandiseUseCases(
    val getMerchandiseUseCase: GetMerchandiseUseCase,
    val deleteMerchandiseUseCase: DeleteMerchandiseUseCase,
    val insertMerchandiseUseCase: InsertMerchandiseUseCase,
    val validateMerchandiseUseCase: ValidateMerchandiseUseCase
)
