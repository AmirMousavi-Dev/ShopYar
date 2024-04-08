package ir.codroid.merchandise_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.codroid.ShopYarDatabase
import ir.codroid.merchandise_data.repository.MerchandiseRepositoryImpl
import ir.codroid.merchandise_domain.repository.MerchandiseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MerchandiseDataModule {


    @Singleton
    @Provides
    fun provideMerchandiseRepository(
        db: ShopYarDatabase,
    ): MerchandiseRepository = MerchandiseRepositoryImpl(
        dao = db.merchandiseDao
    )

}