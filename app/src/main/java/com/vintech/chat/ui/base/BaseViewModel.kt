package com.vintech.chat.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

open class BaseViewModel : ViewModel() {
    private val disposables = arrayListOf<Disposable>()
    private val _contentState = MutableLiveData<PageState>(PageState.StateInitPageData)
    val pageState : LiveData<PageState> = _contentState

    override fun onCleared() {
        super.onCleared()
        disposables.forEach {
            it.dispose()
        }
        disposables.clear()
    }

    fun error() {

    }

    fun refresh() {}

    fun hideLoading() {
        _contentState.postValue(PageState.StateInitPageDataSuccess)
    }

    fun loadMore() {

    }

    fun <T : Any> Single<T>.start(
        pageStateOnListening: PageState = PageState.StateDoActionOnPage,
        onSuccess: (result: T) -> Unit
    ) {
        _contentState.postValue(pageStateOnListening)
        disposables.add(subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            _contentState.postValue(PageState.StateInitPageDataSuccess)
            onSuccess(it)
        }, {
            _contentState.postValue(PageState.StateInitPageDataFailed(it))
        }))
    }
}