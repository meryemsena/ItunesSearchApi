package com.sena.itunes.data;

import com.sena.itunes.data.remote.SearchResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Sena KILIÇ on 1/9/2022.
 */

public interface ItunesApiService {
    @Headers({"Content-Type:application/json; charset=utf-8", "Content-Length:87"})
    @GET("/search?")
    Single<SearchResult> searchContent(@Query("term") String term, @Query("offset") int offset, @Query("limit") int limit);
}
