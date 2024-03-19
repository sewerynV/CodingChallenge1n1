package com.seweryn.piotr.codingchallenge.presentation.salesmanlist.mapper

import com.seweryn.piotr.codingchallenge.presentation.mapper.Mapper
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SalesmanData
import com.seweryn.piotr.domain.model.Salesman
import javax.inject.Inject

interface SalesmanListScreenMapper : Mapper<SalesmanListScreenMapper.Params, List<SalesmanData>> {
  data class Params(
    val salesmanList: List<Salesman>,
  )
}

class SalesmanListScreenMapperImpl @Inject constructor() :
  SalesmanListScreenMapper {

  override fun invoke(params: SalesmanListScreenMapper.Params): List<SalesmanData> =
    params.salesmanList.map { salesman ->
      SalesmanData(
        initial = salesman.name.first().toString(),
        name = salesman.name,
        workingAreas = salesman.areas.joinToString(),
      )
    }
}