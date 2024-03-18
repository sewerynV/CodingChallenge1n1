package com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model

data class SalesmanListData(
  val searchTerm: String = "",
  val list: List<SalesmanData> = emptyList(),
)