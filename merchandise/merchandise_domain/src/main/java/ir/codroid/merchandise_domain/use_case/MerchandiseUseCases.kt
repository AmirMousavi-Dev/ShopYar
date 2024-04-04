package ir.codroid.merchandise_domain.use_case

data class MerchandiseUseCases(
    val getMerchandiseListUseCase: GetMerchandiseListUseCase,
    val getMerchandiseUseCase: GetMerchandiseUseCase,
    val deleteMerchandiseUseCase: DeleteMerchandiseUseCase,
    val insertMerchandiseUseCase: InsertMerchandiseUseCase,
    val validateMerchandiseUseCase: ValidateMerchandiseUseCase
)
