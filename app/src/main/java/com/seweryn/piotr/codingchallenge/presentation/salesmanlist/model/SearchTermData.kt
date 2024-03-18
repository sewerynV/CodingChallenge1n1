package com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model

data class SearchTermData(
  val searchTerm: String = "",
  val onSearchTermChanged: (String) -> Unit = {},
)