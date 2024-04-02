package ir.codroid.merchandise_data.repository

import ir.codroid.core.domain.usecase.BitMapToStringUseCase
import ir.codroid.core.domain.usecase.StringToBitMapUseCase
import ir.codroid.dao.MerchandiseDao
import ir.codroid.merchandise_data.mapper.toMerchandise
import ir.codroid.merchandise_data.mapper.toMerchandiseEntity
import ir.codroid.merchandise_domain.model.Merchandise
import ir.codroid.merchandise_domain.repository.MerchandiseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MerchandiseRepositoryImpl @Inject constructor(
    private val dao: MerchandiseDao,
    private val bitMapToStringUseCase: BitMapToStringUseCase,
    private val stringToBitMapUseCase: StringToBitMapUseCase
) : MerchandiseRepository {
    override  fun getMerchandise(code: String): Flow<List<Merchandise>> =
        dao.getMerchandise(code)
            .map { entities ->
                entities.map {
                    it.toMerchandise(stringToBitMapUseCase)
                }
            }


    override suspend fun insertMerchandise(merchandise: Merchandise) {
        dao.insertMerchandise(merchandise.toMerchandiseEntity(bitMapToStringUseCase))
    }

    override suspend fun deleteMerchandise(merchandise: Merchandise) {
        dao.deleteMerchandise(merchandise.toMerchandiseEntity(bitMapToStringUseCase))
    }
}