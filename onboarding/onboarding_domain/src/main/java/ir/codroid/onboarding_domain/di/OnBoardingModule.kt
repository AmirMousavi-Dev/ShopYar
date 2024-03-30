package ir.codroid.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ir.codroid.onboarding_domain.use_case.ValidateShopInfoUseCase

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingModule {

    @Provides
    @ViewModelScoped
    fun provideValidateShopInfoUseCase() = ValidateShopInfoUseCase()
}