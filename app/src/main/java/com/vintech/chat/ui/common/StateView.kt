package com.vintech.chat.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.vintech.chat.ui.base.BaseViewModel
import com.vintech.chat.ui.base.PageState
import com.vintech.chat.ui.dialog.SpinnerLoading

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StateView(
    modifier: Modifier = Modifier,
    baseViewModel: BaseViewModel,
    enableLoadMore: Boolean = false,
    contentSuccess: LazyListScope.() -> Unit,
    contentEmpty: @Composable () -> Unit = { ContentEmptyDefault() },
    contentError: @Composable () -> Unit = { ContentErrorDefault { baseViewModel.error() } },
    contentLoading: @Composable () -> Unit = { ContentLoadingDefault() }
) {
    val state = baseViewModel.pageState.observeAsState(initial = PageState.StateInitPageData)
    val isRefreshing = state.value == PageState.StatePageRefreshData
    val pullRefreshState = rememberPullRefreshState(
        isRefreshing,
        { baseViewModel.refresh() }
    )
    val listState = rememberLazyListState()
    SpinnerLoading(baseVm = baseViewModel)
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .then(modifier)
    ) {
        when {
            state.value is PageState.StateInitPageData -> contentLoading()
            state.value is PageState.StateInitPageDataEmpty -> contentEmpty()
            state.value is PageState.StateInitPageDataFailed -> contentError()
            state.value is PageState.StateInitPageDataSuccess -> {
                LazyColumn(content = contentSuccess, state = listState)
                if (enableLoadMore) {
                    listState.OnBottomReached {
                        baseViewModel.loadMore()
                    }
                }
            }
        }
    }
}

@Composable
fun LazyListState.OnBottomReached(buffer: Int = 2, loadMore: () -> Unit) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            // subtract buffer from the total items
            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect { if (it) loadMore() }
    }
}

@Composable
fun ContentEmptyDefault() {

}

@Composable
fun ContentLoadingDefault() {

}

@Composable
fun ContentErrorDefault(retry: () -> Unit) {

}