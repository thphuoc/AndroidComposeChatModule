package com.vintech.chat.ui.screens.chatlist

import android.os.Bundle
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vintech.chat.ui.base.BaseActivity
import com.vintech.chat.ui.common.AvatarImageMedium
import com.vintech.chat.ui.common.AvatarImageSmall
import com.vintech.chat.ui.common.OnlineStatus
import com.vintech.chat.ui.theme.AndroidComposeChatModuleTheme
import com.vintech.chat.ui.theme.baseSize
import com.vintech.chat.ui.theme.baseSizeX11
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
        mutableStateListOf(
            ConversationDAO(
                name = "Phan Dang Luu",
                lastMessage = "Hello",
                avatars = listOf("https://yt3.googleusercontent.com/-CFTJHU7fEWb7BYEb6Jh9gm1EpetvVGQqtof0Rbh-VQRIznYYKJxCaqv_9HeBcmJmIsp2vOO9JU=s900-c-k-c0x00ffffff-no-rj")
            ),
            ConversationDAO(
                name = "Giang Nguyen",
                lastMessage = "Xin Chao",
                avatars = listOf(
                    "https://yt3.googleusercontent.com/-CFTJHU7fEWb7BYEb6Jh9gm1EpetvVGQqtof0Rbh-VQRIznYYKJxCaqv_9HeBcmJmIsp2vOO9JU=s900-c-k-c0x00ffffff-no-rj",
                    "https://phantom-marca.unidadeditorial.es/bc7ac39a324ba34f956063664881b200/resize/828/f/jpg/assets/multimedia/imagenes/2022/12/22/16717250934792.jpg"
                )
            )
        )
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
        ConversationAvatar(conversation.avatars)
        Spacer(modifier = Modifier.width(baseSize))
        Column {
            Text(text = conversation.name, modifier = Modifier.padding(0f.dp))
            Text(
                text = conversation.lastMessage,
                modifier = Modifier.alpha(0.5f).padding(0f.dp),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun ConversationAvatar(avatars: List<String>) {
    if (avatars.size == 1) {
        SingleChatConversationAvatar(avatars.first())
    } else {
        MultipleChatConversationAvatar(avatars)
    }
}

@Composable
fun SingleChatConversationAvatar(path: String) {
    Box(modifier = Modifier.size(baseSizeX11)) {
        AvatarImageMedium(path)
        OnlineStatus(modifier = Modifier.align(Alignment.BottomEnd))
    }
}

@Composable
fun MultipleChatConversationAvatar(avatars: List<String>) {
    Box(modifier = Modifier.size(baseSizeX11)) {
        AvatarImageSmall(avatars.first(), Modifier.align(Alignment.TopEnd))
        AvatarImageSmall(avatars.last(), Modifier.align(Alignment.BottomStart))
        OnlineStatus(modifier = Modifier.align(Alignment.BottomEnd))
    }
}