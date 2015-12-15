package com.sefagurel.rsshaber_sondakika;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sefagurel.rsshaber_sondakika.api.MyApi;
import com.sefagurel.rsshaber_sondakika.fragments.SearchFragment;
import com.sefagurel.rsshaber_sondakika.models.NewsModel;
import com.sefagurel.rsshaber_sondakika.models.SearchModel;
import com.sefagurel.rsshaber_sondakika.tools.Consts;

public class MainActivity extends AppCompatActivity {

	private SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		Fragment layout1 = new SearchFragment();
		fragmentTransaction.replace(R.id.container, layout1);
		fragmentTransaction.commit();

	}

	public void getData() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(Consts.SEARCH_URL).addConverterFactory(GsonConverterFactory.create()).build();

		MyApi service = retrofit.create(MyApi.class);

		Call<NewsModel> repos = service.getRSSData("feed/http://donanimhaber.com/rss/tum/", "10");

		repos.enqueue(new Callback<NewsModel>() {
			@Override
			public void onResponse(Response<NewsModel> response, Retrofit retrofit) {
				System.out.println("");
			}

			@Override
			public void onFailure(Throwable t) {
				System.out.println("");
			}
		});
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		}
		else {
			super.onBackPressed();
		}
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// int id = item.getItemId();
	//
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

}
