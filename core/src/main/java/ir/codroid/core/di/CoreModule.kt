package ir.codroid.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.codroid.core.data.preferences.DatastorePreferences
import ir.codroid.core.domain.preferences.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): Preferences =
        DatastorePreferences(context)
}