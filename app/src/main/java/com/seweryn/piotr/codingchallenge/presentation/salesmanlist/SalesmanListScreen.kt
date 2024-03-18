package com.seweryn.piotr.codingchallenge.presentation.salesmanlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SalesmanListScreen(viewModel: SalesmanListViewModel) {
  val data by viewModel.state.collectAsStateWithLifecycle()
}