package ir.codroid.merchandise_domain.use_case

import ir.codroid.merchandise_domain.model.Merchandise
import ir.codroid.merchandise_domain.repository.MerchandiseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMerchandiseUseCase @Inject constructor(
    private val repository: MerchandiseRepository
) {

    operator fun invoke(id: Int): Flow<Merchandise> {
        return repository.getMerchandise(id)
    }
}