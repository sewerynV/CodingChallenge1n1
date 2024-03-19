package com.seweryn.piotr.codingchallenge.presentation.di

import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.mapper.SalesmanListScreenMapper
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.mapper.SalesmanListScreenMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {
  @Provides
  @Singleton
  fun provideSalesmanListScreenMapper(): SalesmanListScreenMapper =
    SalesmanListScreenMapperImpl()
}