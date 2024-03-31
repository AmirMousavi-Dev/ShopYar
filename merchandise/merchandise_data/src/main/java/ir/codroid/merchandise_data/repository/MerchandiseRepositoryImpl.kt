package ir.codroid.merchandise_data.repository

import ir.codroid.database.dao.MerchandiseDao
import ir.codroid.merchandise_data.mapper.toMerchandise
import ir.codroid.merchandise_data.mapper.toMerchandiseEntity
import ir.codroid.merchandise_domain.model.Merchandise
import ir.codroid.merchandise_domain.repository.MerchandiseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MerchandiseRepositoryImpl(
    private val dao: MerchandiseDao
) : MerchandiseRepository {
    override suspend fun getMerchandise(code: String): Flow<List<Merchandise>> =
        dao.getMerchandise(code)
            .map { entities ->
                entities.map {
                    it.toMerchandise()
                }
            }


    override suspend fun insertMerchandise(merchandise: Merchandise) {
        dao.insertMerchandise(merchandise.toMerchandiseEntity())
    }

    override suspend fun deleteMerchandise(merchandise: Merchandise) {
        dao.deleteMerchandise(merchandise.toMerchandiseEntity())
    }
}