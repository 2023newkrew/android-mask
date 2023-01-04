package com.survivalcoding.maskinfo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.maskinfo.data.model.Store
import kotlinx.coroutines.delay


// 로딩 시 시간이 걸리는 것을 묘사하기 위해 1.5초 딜레이를 줍니다.
private const val LOAD_DELAY_MILLIS = 1_500L
private const val STARTING_PAGE = 0

class StorePagingSource(private val service: StoreService) : PagingSource<Int, Store>() {

    override fun getRefreshKey(state: PagingState<Int, Store>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Store> {

        val nowPage = params.key ?: STARTING_PAGE

        // 로딩 시 시간이 걸리는 것을 묘사하기 위해 딜레이를 줍니다.
        if (nowPage != STARTING_PAGE) delay(LOAD_DELAY_MILLIS)

        return try {
            val response = service.getResultMaskStock(nowPage, params.loadSize)

            LoadResult.Page(
                // 여기서 검사하는게 맞는지 모르겠음..
                data = response.stores.filter {
                    it.remain_stat == "few" || it.remain_stat == "some" || it.remain_stat == "plenty"
                }, prevKey = if (nowPage == 0) null else nowPage - 1, nextKey = nowPage + 1
            )
        } catch (e: Exception) {
            println(e.message)
            LoadResult.Error(e)
        }
    }
}