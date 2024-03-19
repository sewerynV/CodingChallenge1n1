package com.seweryn.piotr.domain

import com.seweryn.piotr.domain.model.Salesman
import org.junit.jupiter.api.Test

class SalesmanFilterImplTest {

  private val filter = SalesmanFilterImpl()

  private val salesmanList = listOf(
    Salesman(
      name = "1",
      areas = listOf("12345", "541"),
    ),
    Salesman(
      name = "2",
      areas = listOf("7651*"),
    ),
    Salesman(
      name = "3",
      areas = listOf("90123", "7218*"),
    ),
    Salesman(
      name = "4",
      areas = listOf("5*"),
    ),
    Salesman(
      name = "5",
      areas = listOf("54*"),
    )
  )

  @Test
  fun `should filter salesmen with matching area code`() {
    // GIVEN
    val area = "542"

    // WHEN
    val result = filter.filter(salesmanList, area)

    // THEN
    assert(result.size == 2)
  }

  @Test
  fun `should return empty result when no salesman matching area`() {
    // GIVEN
    val area = "2"

    // WHEN
    val result = filter.filter(salesmanList, area)

    // THEN
    assert(result.isEmpty())
  }
}