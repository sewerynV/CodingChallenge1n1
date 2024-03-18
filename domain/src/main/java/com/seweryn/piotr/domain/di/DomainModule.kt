package com.seweryn.piotr.domain.di

import com.seweryn.piotr.domain.SalesmanFilter
import com.seweryn.piotr.domain.SalesmanFilterImpl
import com.seweryn.piotr.domain.repository.SalesmanRepository
import com.seweryn.piotr.domain.usecase.GetSalesmanListUseCase
import com.seweryn.piotr.domain.usecase.GetSalesmanListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

  @Provides
  @Singleton
  internal fun provideSalesmanFilter(): SalesmanFilter =
    SalesmanFilterImpl()

  @Provides
  @Singleton
  internal fun provideGetSalesmanListUseCase(
    salesmanRepository: SalesmanRepository,
    salesmanFilter: SalesmanFilter,
  ): GetSalesmanListUseCase = GetSalesmanListUseCaseImpl(
    repository = salesmanRepository,
    salesmanFilter = salesmanFilter,
  )

}