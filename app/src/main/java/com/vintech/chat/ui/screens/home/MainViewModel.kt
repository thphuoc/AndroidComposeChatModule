package com.vintech.chat.ui.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vintech.chat.ui.base.BaseViewModel
import com.vintech.chat.ui.base.PageState


class MainViewModel(private val useCase: com.vintech.data.usecase.MainUseCase) : BaseViewModel() {
    private val _itemList = MutableLiveData(mutableStateListOf("Item1", "Item2"))
    val items: LiveData<SnapshotStateList<String>> = _itemList
    var index = 0

    fun loadData(form: com.vintech.data.form.MainForm) {
        useCase.execute(form)
            .start(if (index == 1) PageState.StateInitPageData else PageState.StateDoActionOnPage) {
                index++
                val value = mutableStateListOf<String>()
                value.addAll(it)
                _itemList.postValue(value)
            }
    }
}