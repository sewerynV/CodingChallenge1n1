package com.seweryn.piotr.domain.usecase

import com.seweryn.piotr.domain.SalesmanFilter
import com.seweryn.piotr.domain.model.Salesman
import com.seweryn.piotr.domain.repository.SalesmanRepository

interface GetSalesmanListUseCase : SuspendUseCase<GetSalesmanListUseCase.Params, List<Salesman>> {
  data class Params(
    val area: String,
  )
}

internal class GetSalesmanListUseCaseImpl(
  private val repository: SalesmanRepository,
  private val salesmanFilter: SalesmanFilter,
) : GetSalesmanListUseCase {
  override suspend fun invoke(params: GetSalesmanListUseCase.Params): Result<List<Salesman>> =
    repository.getSalesmanList().runCatching {
      salesmanFilter.filter(
        list = this,
        area = params.area,
      )
    }
}