package com.seweryn.piotr.domain

import com.seweryn.piotr.domain.model.Salesman

internal interface SalesmanFilter {
  fun filter(list: List<Salesman>, area: String): List<Salesman>
}

internal class SalesmanFilterImpl : SalesmanFilter {
  override fun filter(list: List<Salesman>, area: String): List<Salesman> =
    list.filter { salesman ->
      salesman.areas.any { salesmanArea ->
        salesmanArea.startsWith(area)
      }
    }

}