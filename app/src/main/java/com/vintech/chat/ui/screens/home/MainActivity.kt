package com.vintech.chat.ui.screens.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.vintech.chat.ui.base.BaseActivity
import com.vintech.chat.ui.dialog.SpinnerLoading
import com.vintech.chat.ui.dialog.StateLoading
import com.vintech.chat.ui.screens.chatlist.ChatListActivity
import com.vintech.chat.ui.theme.AndroidComposeChatModuleTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeChatModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting {
                        MainList()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Greeting(content: @Composable ()->Unit) {
    val viewModel: MainViewModel = getViewModel()
    val context = LocalContext.current
    SpinnerLoading(baseVm = viewModel)
    Scaffold(topBar = {
        SmallTopAppBar(navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }, title = { Text(text = "TitleBar") })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            //viewModel.loadData(com.vintech.data.form.MainForm())
            context.startActivity(Intent(context, ChatListActivity::class.java))
        }, shape = CircleShape) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }, bottomBar = {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    Dp(54f)
                )
        ) {
            Text(text = "Item1")
            Text(text = "Item2")
            Text(text = "Item3")

        }
    }) {
        StateLoading(loadingState = viewModel) {
            content()
        }
    }
}

@Composable
fun MainList() {
    val viewModel: MainViewModel = getViewModel()
    val _items = viewModel.items.observeAsState(initial = remember { mutableStateListOf() })
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        items(_items.value) {
            AnimatedItem(item = it)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidComposeChatModuleTheme {
        Greeting {
            MainList()
        }
    }
}