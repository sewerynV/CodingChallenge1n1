package com.seweryn.piotr.domain

import com.seweryn.piotr.domain.model.Salesman

internal interface SalesmanFilter {
  fun filter(list: List<Salesman>, area: String): List<Salesman>
}