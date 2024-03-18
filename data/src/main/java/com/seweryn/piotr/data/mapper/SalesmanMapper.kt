package com.seweryn.piotr.data.mapper

import com.seweryn.piotr.data.model.Salesman

typealias DomainSalesman = com.seweryn.piotr.domain.model.Salesman

fun List<Salesman>.toDomain() = map { salesman ->
  salesman.toDomain()
}

fun Salesman.toDomain() = DomainSalesman(
  name = name,
  areas = areas,
)