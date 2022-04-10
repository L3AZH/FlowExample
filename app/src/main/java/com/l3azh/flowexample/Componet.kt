package com.l3azh.flowexample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun LoadingDialog(isShow: Boolean) {
    if (isShow) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(15.dp),
                backgroundColor = Color.LightGray
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(10.dp)
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Loading ...", fontSize = 14.sp, color = Color.Magenta)
                }
            }
        }
    }
}

@Composable
fun ListFakeDataUI(data: List<FakeData>) {
    LazyColumn {
        items(items = data) { item: FakeData ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .size(30.dp),
                    shape = CircleShape,
                    backgroundColor = Color.Green,
                    elevation = 12.dp
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                    ) {
                        Text(text = item.id, modifier = Modifier.align(alignment = Alignment.Center))
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(1f)
                ) {
                    Text(text = item.fistName)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = item.lastName)
                }
            }
        }
    }
}