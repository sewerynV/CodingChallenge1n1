package com.seweryn.piotr.domain.usecase

import com.seweryn.piotr.domain.SalesmanFilter
import com.seweryn.piotr.domain.model.Salesman
import com.seweryn.piotr.domain.repository.SalesmanRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class GetSalesmanListUseCaseImplTest {

  private val repository: SalesmanRepository = mockk(relaxed = true)
  private val filter: SalesmanFilter = mockk(relaxed = true)

  private val useCase = GetSalesmanListUseCaseImpl(
    repository = repository,
    salesmanFilter = filter,
  )

  private val repositoryResult = listOf(Salesman("repository", listOf()))
  private val filterResult = listOf(Salesman("filter", listOf()))

  init {
    every {
      repository.getSalesmanList()
    } returns repositoryResult
    every {
      filter.filter(any(), any())
    } returns filterResult
  }

  @Test
  fun `should return empty list when params are empty`() {
    runBlocking {
      // WHEN
      val result = useCase(
        GetSalesmanListUseCase.Params("")
      )

      // THEN
      assert(result.isSuccess)
      assert(result.getOrNull()?.isEmpty() == true)
    }
  }

  @Test
  fun `should not get salesman list from repository and filter it when params are empty`() {
    runBlocking {
      // WHEN
      val result = useCase(
        GetSalesmanListUseCase.Params("")
      )

      // THEN
      verify(exactly = 0) {
        repository.getSalesmanList()
      }
      verify(exactly = 0) {
        filter.filter(any(), any())
      }
    }
  }

  @Test
  fun `should get salesman list from repository and filter it when params are not empty`() {
    // GIVEN
    val searchTerm = "123"
    runBlocking {
      // WHEN
      val result = useCase(
        GetSalesmanListUseCase.Params(searchTerm)
      )

      // THEN
      verifyOrder {
        repository.getSalesmanList()
        filter.filter(repositoryResult, searchTerm)
      }
    }
  }

  @Test
  fun `should return filtered list when params are not empty`() {
    runBlocking {
      // WHEN
      val result = useCase(
        GetSalesmanListUseCase.Params("123")
      )

      // THEN
      assert(result.isSuccess)
      assert(result.getOrNull() == filterResult)
    }
  }

  @Test
  fun `should return failure when repository throws error`() {
    // GIVEN
    val exception = Exception("repositoryError")
    every {
      repository.getSalesmanList()
    } throws exception
    runBlocking {
      // WHEN
      val result = useCase(
        GetSalesmanListUseCase.Params("123")
      )

      // THEN
      assert(result.isFailure)
      assert(result.exceptionOrNull() == exception)
    }
  }

  @Test
  fun `should return failure when filter throws error`() {
    // GIVEN
    val exception = Exception("filterError")
    every {
      filter.filter(any(), any())
    } throws exception
    runBlocking {
      // WHEN
      val result = useCase(
        GetSalesmanListUseCase.Params("123")
      )

      // THEN
      assert(result.isFailure)
      assert(result.exceptionOrNull() == exception)
    }
  }
}