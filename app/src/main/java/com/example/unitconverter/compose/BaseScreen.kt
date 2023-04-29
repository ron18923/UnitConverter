package com.example.unitconverter.compose

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitconverter.ConverterViewModel
import com.example.unitconverter.ConverterViewModelFactory
import com.example.unitconverter.compose.converter.TopScreen
import com.example.unitconverter.compose.history.HistoryScreen

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ConverterViewModelFactory,
    converterViewModel: ConverterViewModel = viewModel(factory = viewModelFactory)
) {
    val list = converterViewModel.getConversions()
    val historyList = converterViewModel.resultList.collectAsState(initial = emptyList())

    val configuration = LocalConfiguration.current
    var isLandscape by remember { mutableStateOf(false) }

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            isLandscape = true
            Row(
                modifier = modifier
                    .padding(30.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                ) { message1, message2 ->
                    Log.d("MYTAG", "save")
                    converterViewModel.addResult(message1, message2)
                }
                Spacer(modifier = modifier.width(10.dp))
                HistoryScreen(
                    historyList,
                    onCloseTask = { item ->
                        converterViewModel.deleteResult(item)
                    },
                    {
                        converterViewModel.deleteAll()
                    })
            }
        }
        else -> {
            isLandscape = false
            Column(modifier = modifier.padding(30.dp)) {
                TopScreen(
                    list,
                    converterViewModel.selectedConversion,
                    converterViewModel.inputText,
                    converterViewModel.typedValue,
                    isLandscape
                ) { message1, message2 ->
                    Log.d("MYTAG", "save")
                    converterViewModel.addResult(message1, message2)
                }
                Spacer(modifier = modifier.height(20.dp))
                HistoryScreen(
                    historyList,
                    onCloseTask = { item ->
                        converterViewModel.deleteResult(item)
                    },
                    {
                        converterViewModel.deleteAll()
                    })
            }
        }
    }
}