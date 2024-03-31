package ir.codroid.merchandise_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.codroid.database.dao.MerchandiseDao
import ir.codroid.merchandise_data.repository.MerchandiseRepositoryImpl
import ir.codroid.merchandise_domain.repository.MerchandiseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MerchandiseDataModule {

    @Provides
    @Singleton
    fun provideMerchandiseRepository(
        merchandiseDao: MerchandiseDao
    ): MerchandiseRepository =
        MerchandiseRepositoryImpl(merchandiseDao)
}