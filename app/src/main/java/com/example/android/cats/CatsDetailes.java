package com.example.android.cats;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class CatsDetailes extends AppCompatActivity {
    TextView description;
    ImageView image;
    TextView name;
    TextView country_code;
    TextView temperament;
    TextView wikipedia_url;
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats_detailes);

        setToolbar();
        getCatDetailes();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cat filter");
    }

    private void getCatDetailes() {
        image = findViewById(R.id.ivCat);
        name = findViewById(R.id.tvName);
        description = findViewById(R.id.tvDescription);
        country_code = findViewById(R.id.tvCountryCode);
        temperament = findViewById(R.id.tvTemperamentDescription);
        wikipedia_url = findViewById(R.id.tvWikipedia);

        Cat cat = (Cat) getIntent().getSerializableExtra("cat");
        if (cat != null && cat.getImage() != null) {
            Picasso.get().load(cat.getImage().getUrl()).into(image);
            description.setText(cat.getDescription());
            name.setText(cat.getName());
            country_code.setText(cat.getCountry_code());
            temperament.setText(cat.getTemperament());
            wikipedia_url.setText(cat.getWikipedia_url());
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() != android.R.id.home) {
            return super.onOptionsItemSelected(item);
        }
        finish();
        return true;
    }
}

