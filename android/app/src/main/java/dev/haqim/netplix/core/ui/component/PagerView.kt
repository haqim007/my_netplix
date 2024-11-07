package dev.haqim.netplix.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import dev.haqim.netplix.R
import dev.haqim.netplix.core.ui.component.error.EmptyView
import dev.haqim.netplix.core.ui.component.error.ErrorView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Any> PagerView(
    modifier: Modifier,
    pagingItems: LazyPagingItems<T>,
    onTryAgain: () -> Unit,
    onEmpty: (() -> Unit)? = null,
    onResetFilter: (() -> Unit)? = null,
    emptyDataMessage: String = stringResource(R.string.oops_empty),
    content: @Composable (modifier: Modifier) -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()

    if(pullToRefreshState.isRefreshing){
        pagingItems.refresh()
    }

    LaunchedEffect(key1 = Unit) {
        if (
            pagingItems.itemCount > 0 &&
            (pagingItems.loadState.refresh != LoadState.Loading ||
                    pagingItems.loadState.append != LoadState.Loading)
        ){
            pagingItems.refresh()
        }
    }

    LaunchedEffect(pagingItems.loadState) {
        when (pagingItems.loadState.refresh) {
            is LoadState.Loading -> Unit
            is LoadState.Error, is LoadState.NotLoading -> {
                pullToRefreshState.endRefresh()
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        if (pagingItems.loadState.refresh is LoadState.Loading) {
            if (pagingItems.itemCount < 1) {
                LoadingView()
            }
        } else if (pagingItems.loadState.refresh is LoadState.Error) {
            ErrorView(
                message = stringResource(id = R.string.failed_to_load_data),
                onTryAgain = onTryAgain
            )
        } else if (
            pagingItems.loadState.refresh is LoadState.NotLoading &&
            pagingItems.itemCount == 0
        ) { // empty?
            EmptyView(
                message = emptyDataMessage,
                onTryAgain = onEmpty,
                onSecondaryAction = null
            )

        }else{
            content(Modifier)
        }
    }
}