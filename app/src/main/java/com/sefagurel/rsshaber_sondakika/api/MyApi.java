package com.sefagurel.rsshaber_sondakika.api;

import com.sefagurel.rsshaber_sondakika.models.search.SearchModel;
import com.sefagurel.rsshaber_sondakika.models.news.NewsModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Sefa on 12.12.2015.
 */
public interface MyApi {

	@GET("/v3/search/feeds?organic=true")
	Call<SearchModel> getSearchResult(@Query("q") String search, @Query("n") String count);

	@GET("/v3/streams/contents?ck=1449772876361&ct=feedly.mobile.android.1&cv=31.1.0&ranked=newest")
	Call<NewsModel> getRSSData(@Query("streamId") String streamId, @Query("count") String count);

}
