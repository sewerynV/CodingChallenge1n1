package com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model

data class SalesmanData(
  val initial: String,
  val name: String,
  val workingAreas: String,
  val expanded: Boolean = false,
)