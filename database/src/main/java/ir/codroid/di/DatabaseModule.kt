package ir.codroid.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.codroid.ShopYarDatabase
import ir.codroid.dao.MerchandiseDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideShopYarDatabase(app:Application): ShopYarDatabase =
        Room.databaseBuilder(
            app,
            ShopYarDatabase::class.java,
            ShopYarDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideMerchandiseDao(database: ShopYarDatabase): MerchandiseDao =
        database.merchandiseDao

}