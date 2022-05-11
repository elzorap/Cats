package com.example.android.cats;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String API_URL = "https://api.thecatapi.com/";
    public CatsAdapter adapter;
    public RecyclerView cats_recyclerview;
    ArrayList<Cat> listOfCats;
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        cats_recyclerview = findViewById(R.id.rvCats);
        cats_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        getCatsResponse();
    }

    private void getCatsResponse() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        new Retrofit.Builder().baseUrl(API_URL).client(new OkHttpClient.Builder().addInterceptor(interceptor).build()).addConverterFactory(GsonConverterFactory.create()).build().create(CatAPI.class).getCats().enqueue(new Callback<List<Cat>>() {

            public void onResponse(@NotNull Call<List<Cat>> call, @NotNull Response<List<Cat>> response) {
                assert response.body() != null;
                listOfCats = new ArrayList<>(response.body());
                adapter = new CatsAdapter(listOfCats);
                cats_recyclerview.setAdapter(adapter);
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
            }

            public void onFailure(@NotNull Call<List<Cat>> call, @NotNull Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(MainActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "conversion issue! big problems :(", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cat_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        Cat cat = (Cat) getIntent().getSerializableExtra("cat");

            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });

        return true;
    }
}
