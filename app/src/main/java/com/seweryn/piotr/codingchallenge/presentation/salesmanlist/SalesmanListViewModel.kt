package com.seweryn.piotr.codingchallenge.presentation.salesmanlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.mapper.SalesmanListScreenMapper
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SalesmanData
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SearchTermData
import com.seweryn.piotr.domain.usecase.GetSalesmanListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
  override val salesmanList =
    searchTerm
      .debounce(SEARCH_DELAY)
      .map { searchTermData ->
        getSalesmanListUseCase(
          GetSalesmanListUseCase.Params(area = searchTermData.searchTerm)
        ).getOrNull()?.let { list ->
          screenMapper(
            SalesmanListScreenMapper.Params(
              salesmanList = list,
              onSalesmanClick = ::onSalesmanClicked,
            )
          )
        } ?: emptyList()
      }
      .stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(2000),
        emptyList()
      )

  private fun onSearchTermChanged(text: String) {
    searchTerm.value = SearchTermData(
      searchTerm = text,
      onSearchTermChanged = ::onSearchTermChanged
    )
  }

  private fun onSalesmanClicked(name: String) {
    /*salesmanList.value = salesmanList.value.map { data ->
      if(data.name == name) {
        data.copy(expanded = !data.expanded)
      } else {
        data
      }
    }*/
  }

  private companion object {
    const val SEARCH_DELAY = 1000L
  }
}