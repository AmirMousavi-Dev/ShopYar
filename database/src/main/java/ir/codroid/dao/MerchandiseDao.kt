package ir.codroid.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.codroid.entities.MerchandiseEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MerchandiseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMerchandise(merchandiseEntity: MerchandiseEntity)

    @Delete
    suspend fun deleteMerchandise(merchandiseEntity: MerchandiseEntity)

    @Query(
        """
        SELECT *
        FROM merchandiseentity
        WHERE code 
        LIKE :name ||'%'
    """
    )
    fun getMerchandiseList(name: String): Flow<List<MerchandiseEntity>>


    @Query(
        """
        SELECT *
        FROM merchandiseentity
        WHERE id == :id
    """
    )
    fun getMerchandise(id: Int): Flow<MerchandiseEntity>
}