package com.seweryn.piotr.codingchallenge.presentation.salesmanlist

import com.seweryn.piotr.codingchallenge.CoroutineTest
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.mapper.SalesmanListScreenMapper
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SalesmanData
import com.seweryn.piotr.domain.model.Salesman
import com.seweryn.piotr.domain.usecase.GetSalesmanListUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
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
  private val mapperResult2 = listOf(
    SalesmanData(
      name = "mapperResult2",
      initial = "M",
      workingAreas = "areas",
    )
  )
  private val mapperResult3 = listOf(
    SalesmanData(
      name = "mapperResult3",
      initial = "M",
      workingAreas = "areas",
    )
  )

  private val mapperResult4 = listOf(
    SalesmanData(
      name = "mapperResult4",
      initial = "M",
      workingAreas = "areas",
    )
  )

  private val salesManListObserver: (List<SalesmanData>) -> Unit = mockk()

  init {
    coEvery {
      getSalesmanListUseCase(any())
    } returns Result.success(useCaseResult)
    coEvery {
      salesmanListScreenMapper(any())
    } returns mapperResult
    every {
      salesManListObserver(any())
    } returns Unit
  }

  @Test
  fun `should get salesman list from use case and map it when search term changed`() {
    // GIVEN
    val searchTerm = "abc"
    testScope.runTest {
      // WHEN
      viewModel.onSearchTermChanged(searchTerm)
      delay(2000)

      // THEN
      coVerifyOrder {
        getSalesmanListUseCase(
          GetSalesmanListUseCase.Params(searchTerm)
        )
        salesmanListScreenMapper(
          SalesmanListScreenMapper.Params(useCaseResult)
        )
      }
    }
  }

  @Test
  fun `should not get salesman list from use case and map it when search term did not change`() {
    // GIVEN
    val searchTerm = "abc"
    testScope.runTest {
      // WHEN
      viewModel.onSearchTermChanged(searchTerm)
      advanceTimeBy(2000)
      viewModel.onSearchTermChanged(searchTerm)
      advanceTimeBy(2000)

      // THEN
      coVerify(exactly = 1) {
        getSalesmanListUseCase(
          GetSalesmanListUseCase.Params(searchTerm)
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
    val searchTerm = "abc"
    testScope.runTest {
      // WHEN
      viewModel.onSearchTermChanged(searchTerm)
      delay(2000)

      // THEN
      assert(viewModel.salesmanList.value == mapperResult)
    }
  }

  @Test
  fun `should update salesman list with mapped use case data only once per second when search term changed`() {
    testScope.runTest {
      // GIVEN
      val job = launch {
        viewModel.salesmanList.collect {
          salesManListObserver(it)
          println(it)
        }
      }
      // WHEN
      viewModel.onSearchTermChanged("1")
      advanceTimeBy(200)
      every {
        salesmanListScreenMapper(any())
      } returns mapperResult2
      viewModel.onSearchTermChanged("2")
      advanceTimeBy(1200)
      every {
        salesmanListScreenMapper(any())
      } returns mapperResult3
      viewModel.onSearchTermChanged("3")
      advanceTimeBy(300)
      every {
        salesmanListScreenMapper(any())
      } returns mapperResult4
      viewModel.onSearchTermChanged("4")
      advanceTimeBy(2000)

      // THEN
      verify(exactly = 3) {
        salesManListObserver(any())
      }
      job.cancel()
    }
  }
}