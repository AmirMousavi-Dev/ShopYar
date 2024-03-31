package ir.codroid.merchandise_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.codroid.merchandise_domain.repository.MerchandiseRepository
import ir.codroid.merchandise_domain.use_case.DeleteMerchandiseUseCase
import ir.codroid.merchandise_domain.use_case.GetMerchandiseUseCase
import ir.codroid.merchandise_domain.use_case.InsertMerchandiseUseCase
import ir.codroid.merchandise_domain.use_case.MerchandiseUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MerchandiseDomainModule {

    @Provides
    @Singleton
    fun provideGetMerchandiseUseCase(
        merchandiseRepository: MerchandiseRepository
    ): GetMerchandiseUseCase = GetMerchandiseUseCase(merchandiseRepository)

    @Provides
    @Singleton
    fun provideDeleteMerchandiseUseCase(
        merchandiseRepository: MerchandiseRepository
    ): DeleteMerchandiseUseCase = DeleteMerchandiseUseCase(merchandiseRepository)

    @Provides
    @Singleton
    fun provideInsertMerchandiseUseCase(
        merchandiseRepository: MerchandiseRepository
    ): InsertMerchandiseUseCase = InsertMerchandiseUseCase(merchandiseRepository)

    @Provides
    @Singleton
    fun provideMerchandiseUseCases(
        getMerchandiseUseCase: GetMerchandiseUseCase,
        deleteMerchandiseUseCase: DeleteMerchandiseUseCase,
        insertMerchandiseUseCase: InsertMerchandiseUseCase
    ): MerchandiseUseCases = MerchandiseUseCases(
        getMerchandiseUseCase,
        deleteMerchandiseUseCase,
        insertMerchandiseUseCase
    )
}