package com.example.android.cats;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    public CatsAdapter adapter;
    public RecyclerView cats_recyclerview;
    ArrayList<Cat> listOfCats;
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setRecyclerView();
        getCatsResponse();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cat_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

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

    private void filterList(String origin) {
        ArrayList<Cat> filteredListOfCats = new ArrayList<>();

        for (Cat cat : listOfCats) {
            if (cat.getOrigin().toLowerCase().contains(origin)) {
                filteredListOfCats.add(cat);
            }
        }

        adapter = new CatsAdapter(filteredListOfCats);
        cats_recyclerview.setAdapter(adapter);

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.country_all) {
            adapter = new CatsAdapter(listOfCats);
            cats_recyclerview.setAdapter(adapter);
            return true;
        }
        if (id == R.id.country_australia) {
            filterList("australia");
            return true;
        }
        if (id == R.id.country_burma) {
            filterList("burma");
            return true;
        }
        if (id == R.id.country_canada) {
            filterList("canada");
            return true;
        }
        if (id == R.id.country_china) {
            filterList("china");
            return true;
        }
        if (id == R.id.country_cyprus) {
            filterList("cyprus");
            return true;
        }
        if (id == R.id.country_egypt) {
            filterList("egypt");
            return true;
        }
        if (id == R.id.country_france) {
            filterList("france");
            return true;
        }
        if (id == R.id.country_greece) {
            filterList("greece");
            return true;
        }
        if (id == R.id.country_isle_of_man) {
            filterList("isle of man");
            return true;
        }
        if (id == R.id.country_iran) {
            filterList("iran (persia)");
            return true;
        }
        if (id == R.id.country_japan) {
            filterList("japan");
            return true;
        }
        if (id == R.id.country_norway) {
            filterList("norway");
            return true;
        }
        if (id == R.id.country_russia) {
            filterList("russia");
            return true;
        }
        if (id == R.id.country_singapore) {
            filterList("singapore");
            return true;
        }
        if (id == R.id.country_somalia) {
            filterList("somalia");
            return true;
        }
        if (id == R.id.country_thailand) {
            filterList("thailand");
            return true;
        }
        if (id == R.id.country_turkey) {
            filterList("turkey");
            return true;
        }
        if (id == R.id.country_united_arab_emirates) {
            filterList("united arab emirates");
            return true;
        }
        if (id == R.id.country_united_states) {
            filterList("united states");
            return true;
        }
        if (id == R.id.country_united_kingdom) {
            filterList("united kingdom");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cat_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

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

    private void filterList(String origin) {
        ArrayList<Cat> filteredListOfCats = new ArrayList<>();

        for (Cat cat : listOfCats) {
            if (cat.getOrigin().toLowerCase().contains(origin)) {
                filteredListOfCats.add(cat);
            }
        }

        adapter = new CatsAdapter(filteredListOfCats);
        cats_recyclerview.setAdapter(adapter);

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.country_all) {
            adapter = new CatsAdapter(listOfCats);
            cats_recyclerview.setAdapter(adapter);
            return true;
        }
        if (id == R.id.country_australia) {
            filterList("australia");
            return true;
        }
        if (id == R.id.country_burma) {
            filterList("burma");
            return true;
        }
        if (id == R.id.country_canada) {
            filterList("canada");
            return true;
        }
        if (id == R.id.country_china) {
            filterList("china");
            return true;
        }
        if (id == R.id.country_cyprus) {
            filterList("cyprus");
            return true;
        }
        if (id == R.id.country_egypt) {
            filterList("egypt");
            return true;
        }
        if (id == R.id.country_france) {
            filterList("france");
            return true;
        }
        if (id == R.id.country_greece) {
            filterList("greece");
            return true;
        }
        if (id == R.id.country_isle_of_man) {
            filterList("isle of man");
            return true;
        }
        if (id == R.id.country_iran) {
            filterList("iran (persia)");
            return true;
        }
        if (id == R.id.country_japan) {
            filterList("japan");
            return true;
        }
        if (id == R.id.country_norway) {
            filterList("norway");
            return true;
        }
        if (id == R.id.country_russia) {
            filterList("russia");
            return true;
        }
        if (id == R.id.country_singapore) {
            filterList("singapore");
            return true;
        }
        if (id == R.id.country_somalia) {
            filterList("somalia");
            return true;
        }
        if (id == R.id.country_thailand) {
            filterList("thailand");
            return true;
        }
        if (id == R.id.country_turkey) {
            filterList("turkey");
            return true;
        }
        if (id == R.id.country_united_arab_emirates) {
            filterList("united arab emirates");
            return true;
        }
        if (id == R.id.country_united_states) {
            filterList("united states");
            return true;
        }
        if (id == R.id.country_united_kingdom) {
            filterList("united kingdom");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

