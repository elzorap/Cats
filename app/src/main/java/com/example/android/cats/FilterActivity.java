package com.example.android.cats;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {

    public RecyclerView filter_recyclerview;
    FilterAdapter adapter;
    Button btnClear;
    ArrayList<String> catCountryList;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setUpRecyclerView();
        setUpViews();
        clearFilter();
    }
    private void setUpRecyclerView() {
        filter_recyclerview = findViewById(R.id.rvFilters);
        filter_recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpViews() {
        catCountryList = new ArrayList<>();
        catCountryList = getIntent().getStringArrayListExtra("origin");
        adapter = new FilterAdapter(this, catCountryList);
        filter_recyclerview.setAdapter(adapter);

        Toast.makeText(this, catCountryList.size() + "", Toast.LENGTH_LONG).show();
    }

    private void clearFilter() {
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(v->{
            Intent intent = new Intent(FilterActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Toast.makeText(this, catCountryList.size() + "", Toast.LENGTH_LONG).show();
    }

}