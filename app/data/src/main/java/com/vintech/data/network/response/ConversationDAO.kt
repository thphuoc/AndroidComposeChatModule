package com.vintech.data.network.response

data class ConversationDAO(
    val name: String,
    val lastMessage: String,
    val avatars: List<String> = listOf("https://yt3.googleusercontent.com/-CFTJHU7fEWb7BYEb6Jh9gm1EpetvVGQqtof0Rbh-VQRIznYYKJxCaqv_9HeBcmJmIsp2vOO9JU=s900-c-k-c0x00ffffff-no-rj")
)