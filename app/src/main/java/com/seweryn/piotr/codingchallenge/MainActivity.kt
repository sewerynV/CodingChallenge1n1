package com.seweryn.piotr.codingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.SalesmanListScreen
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.SalesmanListViewModel
import com.seweryn.piotr.codingchallenge.presentation.salesmanlist.SalesmanListViewModelImpl
import com.seweryn.piotr.codingchallenge.ui.theme._1und1CodingChallengeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      _1und1CodingChallengeTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          val viewModel: SalesmanListViewModelImpl by viewModels()
          SalesmanListScreen(viewModel = viewModel)
        }
      }
    }
  }
}