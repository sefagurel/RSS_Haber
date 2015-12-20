package com.sefagurel.rsshaber_sondakika.api;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

import com.sefagurel.rsshaber_sondakika.models.NewsModel;
import com.sefagurel.rsshaber_sondakika.models.SearchModel;

/**
 * Created by Sefa on 12.12.2015.
 */
public interface MyApi {

	@GET("/v3/search/feeds")
	Call<SearchModel> getSearchResult(@Query("q") String search, @Query("n") String count);

	@GET("/v3/streams/contents")
	Call<NewsModel> getRSSData(@Query("streamId") String streamId, @Query("count") String count);

}
