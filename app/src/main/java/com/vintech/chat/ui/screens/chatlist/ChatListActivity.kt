package com.vintech.chat.ui.screens.chatlist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.vintech.chat.ui.base.BaseActivity
import com.vintech.chat.ui.theme.AndroidComposeChatModuleTheme
import com.vintech.chat.ui.theme.baseSize
import com.vintech.data.network.response.ConversationDAO

class ChatListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeChatModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatList()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreView() {
    AndroidComposeChatModuleTheme {
        ChatList()
    }
}

@Composable
fun ChatList() {
    //Search
    //List
    val _items = remember {
        mutableStateListOf(ConversationDAO())
    }
    LazyColumn(content = {
        items(_items) {
            ConversationItem(conversation = it)
        }
    })
}

@Composable
fun ConversationItem(conversation: ConversationDAO) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(baseSize), verticalAlignment = Alignment.CenterVertically
    ) {
        ConversationAvatar(listOf("https://yt3.googleusercontent.com/-CFTJHU7fEWb7BYEb6Jh9gm1EpetvVGQqtof0Rbh-VQRIznYYKJxCaqv_9HeBcmJmIsp2vOO9JU=s900-c-k-c0x00ffffff-no-rj"))
        Spacer(modifier = Modifier.width(baseSize))
        Column(verticalArrangement = Arrangement.spacedBy((-5).dp)) {
            Text(text = "Van Duc Luu")
            Text(
                text = "You: https://dad.com.vn",
                modifier = Modifier.alpha(0.5f),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ConversationAvatar(avatars: List<String>) {
    if (avatars.isNotEmpty()) {
        SingleChatConversationAvatar(avatars.first())
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SingleChatConversationAvatar(path: String) {
    Box(modifier = Modifier.size(54f.dp)) {
        GlideImage(
            model = path,
            modifier = Modifier
                .clip(CircleShape)
                .width(54f.dp)
                .height(54f.dp),
            contentDescription = "LoadImage"
        )
        Box(
            modifier = Modifier
                .size(15f.dp).clip(CircleShape)
                .align(Alignment.BottomEnd)
                .background(Color.Green)
                .border(width = 3f.dp, color = Color.White, shape = CircleShape)
        )
    }
}