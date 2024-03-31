package ir.codroid.merchandise_domain.use_case

import ir.codroid.merchandise_domain.model.Merchandise
import ir.codroid.merchandise_domain.repository.MerchandiseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMerchandiseUseCase @Inject constructor(
    private val repository: MerchandiseRepository
) {

    suspend operator fun invoke (code : String) :Flow<List<Merchandise>> {
        return repository.getMerchandise(code)
    }
}