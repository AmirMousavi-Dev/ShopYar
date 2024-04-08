package ir.codroid.merchandise_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ir.codroid.merchandise_domain.repository.MerchandiseRepository
import ir.codroid.merchandise_domain.use_case.DeleteMerchandiseUseCase
import ir.codroid.merchandise_domain.use_case.GetMerchandiseListUseCase
import ir.codroid.merchandise_domain.use_case.GetMerchandiseUseCase
import ir.codroid.merchandise_domain.use_case.InsertMerchandiseUseCase
import ir.codroid.merchandise_domain.use_case.MerchandiseUseCases
import ir.codroid.merchandise_domain.use_case.ValidateMerchandiseUseCase

@Module
@InstallIn(ViewModelComponent::class)
object MerchandiseDomainModule {


    @Provides
    @ViewModelScoped
    fun provideGetMerchandiseListUseCase(
        repository: MerchandiseRepository
    ): GetMerchandiseListUseCase = GetMerchandiseListUseCase(repository)


    @Provides
    @ViewModelScoped
    fun provideGetMerchandiseUseCase(
        repository: MerchandiseRepository
    ): GetMerchandiseUseCase = GetMerchandiseUseCase(repository)


    @Provides
    @ViewModelScoped
    fun provideDeleteMerchandiseUseCase(
        repository: MerchandiseRepository
    ): DeleteMerchandiseUseCase = DeleteMerchandiseUseCase(repository)


    @Provides
    @ViewModelScoped
    fun provideInsertMerchandiseUseCase(
        repository: MerchandiseRepository
    ): InsertMerchandiseUseCase = InsertMerchandiseUseCase(repository)


    @Provides
    @ViewModelScoped
    fun provideMerchandiseUseCases(
        getMerchandiseListUseCase: GetMerchandiseListUseCase,
        getMerchandiseUseCase: GetMerchandiseUseCase,
        deleteMerchandiseUseCase: DeleteMerchandiseUseCase,
        insertMerchandiseUseCase: InsertMerchandiseUseCase
    ): MerchandiseUseCases = MerchandiseUseCases(
        getMerchandiseListUseCase,
        getMerchandiseUseCase,
        deleteMerchandiseUseCase,
        insertMerchandiseUseCase,
        ValidateMerchandiseUseCase()
    )
}