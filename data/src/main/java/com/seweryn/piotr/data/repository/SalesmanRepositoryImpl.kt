package com.seweryn.piotr.data.repository

import com.seweryn.piotr.data.mapper.toDomain
import com.seweryn.piotr.data.storage.SalesmanStorage
import com.seweryn.piotr.domain.model.Salesman
import com.seweryn.piotr.domain.repository.SalesmanRepository

internal class SalesmanRepositoryImpl(
  private val salesmanStorage: SalesmanStorage,
) : SalesmanRepository {
  override fun getSalesmanList(): List<Salesman> =
    salesmanStorage.getSalesmanList().toDomain()
}