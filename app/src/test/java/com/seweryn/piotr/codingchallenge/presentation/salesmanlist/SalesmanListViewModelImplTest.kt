package com.seweryn.piotr.codingchallenge.presentation.salesmanlist

import com.seweryn.piotr.codingchallenge.CoroutineTest
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.mapper.SalesmanListScreenMapper
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SalesmanData
import com.seweryn.piotr.domain.model.Salesman
import com.seweryn.piotr.domain.usecase.GetSalesmanListUseCase
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class SalesmanListViewModelImplTest : CoroutineTest {

  override lateinit var testScope: TestScope
  override lateinit var dispatcher: TestDispatcher

  private val getSalesmanListUseCase: GetSalesmanListUseCase = mockk(relaxed = true)
  private val salesmanListScreenMapper: SalesmanListScreenMapper = mockk(relaxed = true)

  private val viewModel = SalesmanListViewModelImpl(
    getSalesmanListUseCase = getSalesmanListUseCase,
    screenMapper = salesmanListScreenMapper,
  )

  private val useCaseResult = listOf(
    Salesman(
      name = "useCaseResult",
      areas = listOf(),
    )
  )
  private val mapperResult = listOf(
    SalesmanData(
      name = "mapperResult",
      initial = "M",
      workingAreas = "areas",
    )
  )

  init {
    coEvery {
      getSalesmanListUseCase(any())
    } returns Result.success(useCaseResult)
    coEvery {
      salesmanListScreenMapper(any())
    } returns mapperResult
  }

  @Test
  fun `should get salesman list from use case and map it when search term changed`() {
    // GIVEN
    val searTerm = "abc"
    testScope.runTest {
      // WHEN
      viewModel.onSearchTermChanged(searTerm)
      delay(2000)

      // THEN
      coVerifyOrder {
        getSalesmanListUseCase(
          GetSalesmanListUseCase.Params(searTerm)
        )
        salesmanListScreenMapper(
          SalesmanListScreenMapper.Params(useCaseResult)
        )
      }
    }
  }

  @Test
  fun `should update salesman list with mapped use case data when search term changed`() {
    // GIVEN
    val searTerm = "abc"
    testScope.runTest {
      // WHEN
      viewModel.onSearchTermChanged(searTerm)
      delay(2000)

      // THEN
      coVerifyOrder {
        getSalesmanListUseCase(
          GetSalesmanListUseCase.Params(searTerm)
        )
        salesmanListScreenMapper(
          SalesmanListScreenMapper.Params(useCaseResult)
        )
      }
    }
  }
}