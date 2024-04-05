package ir.codroid.merchandise_data.repository

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
) : MerchandiseRepository {
    override fun getMerchandiseList(code: String): Flow<List<Merchandise>> =
        dao.getMerchandiseList(code)
            .map { entities ->
                entities.map {
                    it.toMerchandise()
                }
            }

    override fun getMerchandise(id: Int): Flow<Merchandise> =
        dao.getMerchandise(id)
            .map {
                it.toMerchandise()
            }


    override suspend fun insertMerchandise(merchandise: Merchandise) {
        dao.insertMerchandise(merchandise.toMerchandiseEntity())
    }

    override suspend fun deleteMerchandise(merchandise: Merchandise) {
        dao.deleteMerchandise(merchandise.toMerchandiseEntity())
    }
}