package com.l3azh.flowexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.l3azh.flowexample.ui.theme.FlowExampleTheme

class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .background(Color.Magenta),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Reset", modifier = Modifier
                                    .clickable {
                                        viewModel.resetToBegin()
                                    }
                                    .padding(vertical = 12.dp, horizontal = 10.dp))
                            }
                        }
                    ) {
                        Box(modifier = Modifier.fillMaxSize(1f)) {
                            when {
                                viewModel.state.value.isBeginState -> {
                                    Button(
                                        onClick = {
                                            viewModel.requestGetListData()
                                        },
                                        modifier = Modifier.align(alignment = Alignment.Center)
                                    ) {
                                        Text(text = "Get Data")
                                    }
                                }
                                viewModel.state.value.isLoading -> {
                                    LoadingDialog(isShow = viewModel.state.value.isLoading)
                                    viewModel.getListDataFLow()
                                        .collectAsState(initial = emptyList())
                                }
                                viewModel.state.value.onError -> {
                                    Text(
                                        text = viewModel.state.value.errMessage,
                                        fontSize = 18.sp,
                                        modifier = Modifier.align(alignment = Alignment.Center)
                                    )
                                }
                                viewModel.state.value.onSuccess -> {
                                    viewModel.onGetDataSuccess()
                                }
                                viewModel.state.value.isShowListState -> {
                                    ListFakeDataUI(data = viewModel.listData.value!!)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
