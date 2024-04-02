package ir.codroid.merchandise_domain.use_case

import ir.codroid.merchandise_domain.model.Merchandise
import ir.codroid.merchandise_domain.repository.MerchandiseRepository
import javax.inject.Inject

class InsertMerchandiseUseCase @Inject constructor (
    private val repository: MerchandiseRepository
) {
    suspend operator fun invoke(merchandise: Merchandise): Result<String> {
        return try {
            repository.insertMerchandise(merchandise)
            Result.success("success")
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

}