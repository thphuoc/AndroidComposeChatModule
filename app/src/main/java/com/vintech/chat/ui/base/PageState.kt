package com.vintech.chat.ui.base

sealed class PageState {
    object StateDoActionOnPage : PageState()
    object StateInitPageData : PageState()
    object StatePageRefreshData : PageState()
    object StateInitPageDataSuccess : PageState()
    class StateInitPageDataFailed(ex: Throwable) : PageState()
    object StateInitPageDataEmpty : PageState()
}