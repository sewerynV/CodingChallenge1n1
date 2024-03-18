package com.seweryn.piotr.codingchallenge.presentation.salesmanlist

import androidx.lifecycle.ViewModel
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SalesmanListData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

interface SalesmanListViewModel {
  val state: StateFlow<SalesmanListData>
}

class SalesmanListViewModelImpl @Inject constructor(

) : ViewModel(), SalesmanListViewModel {
  override val state = MutableStateFlow(SalesmanListData())
}