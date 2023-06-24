package com.vintech.chat.ui

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
import com.vintech.chat.BaseViewModel

@Composable
fun SpinnerLoading(loadingState : BaseViewModel) {
    val openState = loadingState.loadingState.observeAsState(LoadingType.HIDE)
    if(openState.value == LoadingType.DIALOG) {
        AlertDialog(
            onDismissRequest = { loadingState.hideLoading() },
            title = {
                Text(text = "Dialog Title")
            },
            text = {
                Text("This is the dialog content.")
            },
            confirmButton = {
                Button(onClick = { loadingState.hideLoading() }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { loadingState.hideLoading() }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun StateLoading(loadingState : BaseViewModel, content: @Composable ()->Unit) {
    val openState = loadingState.loadingState.observeAsState(LoadingType.HIDE)
    if(openState.value == LoadingType.VIEW) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "Loading...")
        }
    } else {
        content()
    }
}