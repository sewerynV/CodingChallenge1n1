package com.seweryn.piotr.codingchallenge.presentation.salesmanlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seweryn.piotr.codingchallenge.R
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SalesmanData
import com.seweryn.piotr.codingchallenge.ui.theme.Grey400
import com.seweryn.piotr.codingchallenge.ui.theme.InitialBackground
import com.seweryn.piotr.codingchallenge.ui.theme.InitialBorder
import com.seweryn.piotr.codingchallenge.ui.theme.Typography

@Composable
fun SalesmanListScreen(viewModel: SalesmanListViewModel) {
  val data by viewModel.state.collectAsStateWithLifecycle()
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
  ) {
    TextField(
      modifier = Modifier
        .padding(
          horizontal = 16.dp,
          vertical = 24.dp,
        )
        .fillMaxWidth(),
      value = data.searchTerm,
      onValueChange = data.onSearchTermChanged,
      textStyle = Typography.labelLarge,
    )
    LazyColumn(
      modifier = Modifier
        .padding(start = 16.dp),
    ) {
      items(data.list) { salesman ->
        SalesmanListItem(data = salesman)
      }
    }
  }
}

@Composable
private fun SalesmanListItem(
  data: SalesmanData,
) {
  Row {
    Box(
      modifier = Modifier
        .size(42.dp)
        .border(1.dp, InitialBorder, CircleShape)
        .background(InitialBackground),
      contentAlignment = Alignment.Center,
    ) {
      Text(
        text = data.initial,
        style = Typography.bodyLarge,
        color = Color.Black,
      )
    }
    Spacer(modifier = Modifier.width(8.dp))
    Text(
      modifier = Modifier.weight(1f),
      text = data.name,
      style = Typography.labelLarge,
      color = Color.Black,
    )
    Icon(
      modifier = Modifier.padding(16.dp),
      painter = painterResource(
        id = if (data.expanded) {
          R.drawable.ic_chevron_down
        } else {
          R.drawable.ic_chevron_right
        },
      ),
      tint = Grey400,
      contentDescription = null
    )
  }
}