package com.seweryn.piotr.data.di

import com.seweryn.piotr.data.repository.SalesmanRepositoryImpl
import com.seweryn.piotr.data.storage.SalesmanStorage
import com.seweryn.piotr.data.storage.SalesmanStorageImpl
import com.seweryn.piotr.domain.repository.SalesmanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

  @Provides
  @Singleton
  internal fun provideSalesmanStorage(): SalesmanStorage =
    SalesmanStorageImpl()

  @Provides
  @Singleton
  internal fun provideSalesmanRepository(
    storage: SalesmanStorage,
  ): SalesmanRepository = SalesmanRepositoryImpl(
    salesmanStorage = storage,
  )

}