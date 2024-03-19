package com.seweryn.piotr.codingchallenge.presentation.salesmanlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.mapper.SalesmanListScreenMapper
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SalesmanData
import com.seweryn.piotr.domain.usecase.GetSalesmanListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

interface SalesmanListViewModel {
  val searchTerm: StateFlow<String>
  val salesmanList: StateFlow<List<SalesmanData>>

  fun onSearchTermChanged(text: String)
  fun onSalesmanClicked(salesman: SalesmanData)
}

@HiltViewModel
class SalesmanListViewModelImpl @Inject constructor(
  private val getSalesmanListUseCase: GetSalesmanListUseCase,
  private val screenMapper: SalesmanListScreenMapper,
) : ViewModel(), SalesmanListViewModel {

  override val searchTerm =
    MutableStateFlow("")
  private val searchFlow =
    searchTerm
      .debounce(SEARCH_DELAY)

  override val salesmanList =
    MutableStateFlow<List<SalesmanData>>(emptyList())

  init {
    viewModelScope.launch {
      searchFlow.collect { searchTerm ->
        getSalesmanListUseCase(
          GetSalesmanListUseCase.Params(area = searchTerm)
        ).getOrNull()?.let { list ->
          salesmanList.value = screenMapper(
            SalesmanListScreenMapper.Params(
              salesmanList = list,
            )
          )
        }
      }
    }
  }

  override fun onSearchTermChanged(text: String) {
    searchTerm.value = text
  }

  override fun onSalesmanClicked(salesman: SalesmanData) {
    salesmanList.value = salesmanList.value.map { data ->
      if (data.name == salesman.name) {
        data.copy(expanded = !data.expanded)
      } else {
        data
      }
    }
  }

  private companion object {
    const val SEARCH_DELAY = 1000L
  }
}