package com.corbin.msu.photogallery

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PhotoPagingSource(
    private val photoRepository: PhotoRepository
) : PagingSource<Int, GalleryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        return try {
            val nextPage = params.key ?: 1
            val response = photoRepository.fetchPhotos(nextPage)
            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return state.anchorPosition
    }
}