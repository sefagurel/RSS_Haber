package com.sefagurel.rsshaber_sondakika.fragments;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sefagurel.rsshaber_sondakika.R;
import com.sefagurel.rsshaber_sondakika.adapters.SearchResultListAdapter;
import com.sefagurel.rsshaber_sondakika.api.MyApi;
import com.sefagurel.rsshaber_sondakika.models.SearchModel;
import com.sefagurel.rsshaber_sondakika.tools.Consts;
import com.sefagurel.rsshaber_sondakika.tools.ItemClickSupport;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Sefa on 15.12.2015.
 */

public class SearchFragment extends Fragment {

	private Activity				activity;
	private RecyclerView			mRecyclerView;
	private SearchView				searchView;
	private SearchResultListAdapter	searchResultListAdapter;
	private int						ColumnCount	= 1;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		activity = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_search, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_search_result_list);
		mRecyclerView.setHasFixedSize(true);

		GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), ColumnCount);
		gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(gridLayoutManager);
	}

	public void search(String query) {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(Consts.SEARCH_URL).addConverterFactory(GsonConverterFactory.create()).build();

		MyApi service = retrofit.create(MyApi.class);

		Call<SearchModel> repos = service.getSearchResult(query, "100");

		repos.enqueue(new Callback<SearchModel>() {
			@Override
			public void onResponse(Response<SearchModel> response, Retrofit retrofit) {

				searchResultListAdapter = new SearchResultListAdapter(getActivity(), response.body());
				mRecyclerView.setAdapter(searchResultListAdapter);

				ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
					@Override
					public void onItemClicked(RecyclerView recyclerView, int position, View v) {
						// Intent i = new Intent(getActivity(), SubSpeciesActivity.class);
						// SpeciesCardListAdapter speciesCardListAdapter = (SpeciesCardListAdapter) recyclerView.getAdapter();
						// SpeciesCardModel speciesCardModel = speciesCardListAdapter.getItemByPosition(position);
						// i.putExtra("SpeciesCardModel", speciesCardModel);
						// startActivity(i);
					}
				});

			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		inflater.inflate(R.menu.search, menu);

		final MenuItem menuItem = menu.findItem(R.id.menu_search_view);
		searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
		SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
		searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
		searchView.setSubmitButtonEnabled(true);
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				searchView.clearFocus();
				search(query);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				return true;
			}
		});

		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.menu_search_view) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
