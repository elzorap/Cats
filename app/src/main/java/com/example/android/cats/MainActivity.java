package com.example.android.cats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public CatsAdapter adapter;
    public RecyclerView cats_recyclerview;
    ArrayList<Cat> listOfCats;
    Toolbar toolbar;
    Button btnFilter;
    ArrayList<String> catCountryList = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setRecyclerView();
        getCatsResponse();
        setUpViews();

    }


    private void setToolbar() {
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
    }

    private void setRecyclerView() {
        cats_recyclerview = findViewById(R.id.rvCats);
        cats_recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getCatsResponse() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        new Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL).client(new OkHttpClient.Builder().addInterceptor(interceptor).build()).addConverterFactory(GsonConverterFactory.create()).build().create(CatAPI.class).getCats().enqueue(new Callback<List<Cat>>() {

            public void onResponse(@NotNull Call<List<Cat>> call, @NotNull Response<List<Cat>> response) {
                assert response.body() != null;
                listOfCats = new ArrayList<>(response.body());
                adapter = new CatsAdapter(listOfCats);
                cats_recyclerview.setAdapter(adapter);
                catCountryList = getCatCountries(listOfCats);
                findViewById(R.id.progressBar).setVisibility(View.GONE);
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

    private void setUpViews() {
        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FilterActivity.class);
            intent.putStringArrayListExtra(Constants.ORIGIN_KEY, catCountryList);
            startActivityForResult(intent, 1000);
        });
    }

    private ArrayList<String> getCatCountries(ArrayList<Cat> list) {
        HashSet<String> countryList = new HashSet<>();

        for (Cat cat : list) {
            countryList.add(cat.getOrigin());
        }

        return new ArrayList<>(countryList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String filter = data.getStringExtra(Constants.FILTER_KEY);
        if (filter.equalsIgnoreCase("")){
            adapter = new CatsAdapter(listOfCats);
            cats_recyclerview.setAdapter(adapter);
        } else {
            filterList(filter);
        }

    }


    private void filterList(String origin) {
        ArrayList<Cat> filteredListOfCats = new ArrayList<>();

        for (Cat cat : listOfCats) {
            if (cat.getOrigin().equalsIgnoreCase(origin)) {
                filteredListOfCats.add(cat);
            }
        }

        adapter = new CatsAdapter(filteredListOfCats);
        cats_recyclerview.setAdapter(adapter);

    }
}

