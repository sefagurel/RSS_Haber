package com.sefagurel.rsshaber_sondakika;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sefagurel.rsshaber_sondakika.api.MyApi;
import com.sefagurel.rsshaber_sondakika.fragments.SearchFragment;
import com.sefagurel.rsshaber_sondakika.models.news.NewsModel;
import com.sefagurel.rsshaber_sondakika.tools.Consts;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    NewsModel newsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getBaseContext();

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
        getData();
    }

    public void getData() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Consts.SEARCH_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        MyApi service = retrofit.create(MyApi.class);

        Call<NewsModel> repos = service.getRSSData("feed/http://donanimhaber.com/rss/tum/", "10");

        repos.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Response<NewsModel> response, Retrofit retrofit) {
                System.out.println("onResponse");
                newsModel = response.body();
                newsModel.Insert();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsModel.Destroy();
    }
}
