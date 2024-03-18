package com.seweryn.piotr.data.storage

import com.seweryn.piotr.data.model.Salesman

internal interface SalesmanStorage {
  fun getSalesmanList(): List<Salesman>
}

internal class SalesmanStorageImpl : SalesmanStorage {
  override fun getSalesmanList(): List<Salesman> = listOf(
    Salesman(
      name = "Artem Titarenko",
      areas = listOf("75133"),
    ),
    Salesman(
      name = "Bernd Schmitt",
      areas = listOf("7619*"),
    ),
    Salesman(
      name = "Chris Krapp",
      areas = listOf("762*"),
    ),
    Salesman(
      name = "Alex Uber",
      areas = listOf("86*"),
    )
  )

}