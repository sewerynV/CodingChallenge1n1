package com.seweryn.piotr.codingchallenge.presentation.salesmanlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.mapper.SalesmanListScreenMapper
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SalesmanData
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SearchTermData
import com.seweryn.piotr.domain.usecase.GetSalesmanListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

interface SalesmanListViewModel {
  val searchTerm: StateFlow<SearchTermData>
  val salesmanList: StateFlow<List<SalesmanData>>
}

@HiltViewModel
class SalesmanListViewModelImpl @Inject constructor(
  private val getSalesmanListUseCase: GetSalesmanListUseCase,
  private val screenMapper: SalesmanListScreenMapper,
) : ViewModel(), SalesmanListViewModel {

  override val searchTerm =
    MutableStateFlow(
      SearchTermData(
        onSearchTermChanged = ::onSearchTermChanged
      )
    )
  private val searchFlow =
    searchTerm
      .debounce(SEARCH_DELAY)

  override val salesmanList =
    MutableStateFlow<List<SalesmanData>>(emptyList())

  init {
    viewModelScope.launch {
      searchFlow.collect { searchTermData ->
        getSalesmanListUseCase(
          GetSalesmanListUseCase.Params(area = searchTermData.searchTerm)
        ).getOrNull()?.let { list ->
          salesmanList.value = screenMapper(
            SalesmanListScreenMapper.Params(
              salesmanList = list,
              onSalesmanClick = ::onSalesmanClicked,
            )
          )
        }
      }
    }
  }

  private fun onSearchTermChanged(text: String) {
    searchTerm.value = SearchTermData(
      searchTerm = text,
      onSearchTermChanged = ::onSearchTermChanged
    )
  }

  private fun onSalesmanClicked(name: String) {
    salesmanList.value = salesmanList.value.map { data ->
      if (data.name == name) {
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