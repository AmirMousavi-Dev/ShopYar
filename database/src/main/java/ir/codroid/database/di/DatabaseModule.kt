package ir.codroid.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.codroid.database.ShopYarDatabase
import ir.codroid.database.dao.MerchandiseDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideShopYarDatabase(@ApplicationContext context: Context): ShopYarDatabase =
        Room.databaseBuilder(
            context,
            ShopYarDatabase::class.java,
            ShopYarDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideMerchandiseDao(database: ShopYarDatabase): MerchandiseDao =
        database.merchandiseDao

}