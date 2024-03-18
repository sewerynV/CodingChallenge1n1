package com.seweryn.piotr.domain.repository

import com.seweryn.piotr.domain.model.Salesman

interface SalesmanRepository {
  fun getSalesmanList(): List<Salesman>
}