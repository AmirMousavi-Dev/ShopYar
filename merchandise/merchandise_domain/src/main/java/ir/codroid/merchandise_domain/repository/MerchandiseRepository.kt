package ir.codroid.merchandise_domain.repository

import ir.codroid.merchandise_domain.model.Merchandise
import kotlinx.coroutines.flow.Flow

interface MerchandiseRepository {

    suspend fun getMerchandise(
        code: String
    ): Flow<List<Merchandise>>

    suspend fun insertMerchandise(merchandise: Merchandise)
    suspend fun deleteMerchandise(merchandise: Merchandise)

}