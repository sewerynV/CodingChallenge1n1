package com.seweryn.piotr.codingchallenge.presentation.salesmanlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seweryn.piotr.codingchallenge.R
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.model.SalesmanData
import com.seweryn.piotr.codingchallenge.ui.theme.Grey200
import com.seweryn.piotr.codingchallenge.ui.theme.Grey400
import com.seweryn.piotr.codingchallenge.ui.theme.InitialBackground
import com.seweryn.piotr.codingchallenge.ui.theme.InitialBorder
import com.seweryn.piotr.codingchallenge.ui.theme.Typography

@Composable
fun SalesmanListScreen(viewModel: SalesmanListViewModel) {
  val salesmanList by viewModel.salesmanList.collectAsStateWithLifecycle()
  val searchTermData by viewModel.searchTerm.collectAsStateWithLifecycle()
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
        .border(
          width = 0.5.dp,
          color = Grey200,
        )
        .shadow(elevation = 5.dp)
        .fillMaxWidth(),
      value = searchTermData.searchTerm,
      onValueChange = searchTermData.onSearchTermChanged,
      textStyle = Typography.labelLarge,
      leadingIcon = {
        Icon(
          painter = painterResource(
            id = R.drawable.ic_search,
          ),
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onBackground,
        )
      },
      trailingIcon = {
        Icon(
          painter = painterResource(
            id = R.drawable.ic_microphone,
          ),
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onBackground,
        )
      },
      colors = TextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
      )
    )
    LazyColumn(
      modifier = Modifier
        .padding(start = 16.dp),
    ) {
      items(salesmanList) { salesman ->
        SalesmanListItem(data = salesman)
      }
    }
  }
}

@Composable
private fun SalesmanListItem(
  data: SalesmanData,
) {
  Row(
    modifier = Modifier.clickable { data.onClicked() },
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Box(
      modifier = Modifier
        .size(42.dp)
        .border(1.dp, InitialBorder, CircleShape)
        .background(InitialBackground, CircleShape),
      contentAlignment = Alignment.Center,
    ) {
      Text(
        text = data.initial,
        style = Typography.bodyLarge,
        color = Color.Black,
      )
    }
    Spacer(modifier = Modifier.width(8.dp))
    Column(
      modifier = Modifier.weight(1f),
      verticalArrangement = if (data.expanded) {
        Arrangement.SpaceBetween
      } else {
        Arrangement.Center
      }
    ) {
      Text(
        text = data.name,
        style = Typography.labelLarge,
        color = Color.Black,
      )
      if (data.expanded) {
        Text(
          text = data.workingAreas,
          style = Typography.labelMedium,
          color = MaterialTheme.colorScheme.onBackground,
        )
      }
    }
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