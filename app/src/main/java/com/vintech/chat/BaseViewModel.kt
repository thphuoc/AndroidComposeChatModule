package com.vintech.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vintech.chat.ui.LoadingType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

open class BaseViewModel : ViewModel() {
    private val disposables = arrayListOf<Disposable>()
    private val _loadingState = MutableLiveData(LoadingType.HIDE)
    val loadingState : LiveData<LoadingType> = _loadingState

    override fun onCleared() {
        super.onCleared()
        disposables.forEach {
            it.dispose()
        }
        disposables.clear()
    }

    fun hideLoading() {
        _loadingState.postValue(LoadingType.HIDE)
    }

    fun <T : Any> Single<T>.listen(
        loadingType: LoadingType = LoadingType.DIALOG,
        onError: (ex: Throwable) -> Unit = {},
        onSuccess: (result: T) -> Unit
    ) {
        _loadingState.postValue(loadingType)
        disposables.add(subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            onSuccess(it)
            _loadingState.postValue(LoadingType.HIDE)
        }, {
            onError(it)
            _loadingState.postValue(LoadingType.HIDE)
        }))
    }
}