package com.vintech.chat.ui.screens.chatlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vintech.chat.ui.base.BaseViewModel
import com.vintech.data.network.response.ConversationDAO
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class ChatListViewModel : BaseViewModel() {
    private val _data = MutableLiveData<List<ConversationDAO>>()
    val data : LiveData<List<ConversationDAO>> = _data
    override fun loadPageData() {
        Single.timer(3, TimeUnit.SECONDS).map {
            listOf(
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
        }.start {
            _data.postValue(it)
        }
    }
}