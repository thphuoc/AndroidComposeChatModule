package com.vintech.chat.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vintech.chat.ui.base.BaseViewModel
import com.vintech.chat.ui.base.PageState

@Composable
fun SpinnerLoading(baseVm : BaseViewModel) {
    val openState = baseVm.pageState.observeAsState()
    println("Phuoc: ${openState.value}")
    if(openState.value is PageState.StateDoActionOnPage) {

        AlertDialog(
            onDismissRequest = { baseVm.hideLoading() },
            title = {
                Text(text = "Dialog Title")
            },
            text = {
                Text("This is the dialog content.")
            },
            confirmButton = {
                Button(onClick = { baseVm.hideLoading() }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { baseVm.hideLoading() }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun StateLoading(loadingState : BaseViewModel, content: @Composable ()->Unit) {
    val openState = loadingState.pageState.observeAsState()
    if(openState.value == PageState.StateInitPageData) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "Loading...")
        }
    } else {
        content()
    }
}