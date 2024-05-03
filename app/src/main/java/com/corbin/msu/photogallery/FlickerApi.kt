package com.corbin.msu.photogallery

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "b7db468b075988f74fc71d1e9382c5a4"

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    suspend fun fetchPhotos(@Query("page") page: Int): FlickrResponse
}