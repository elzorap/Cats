package com.example.android.cats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ItemViewHolder> {
    ArrayList<String> listOfCountry;
    Context context;

    public FilterAdapter(Context context, ArrayList<String> listOfCountry) {
        this.context = context;
        this.listOfCountry = listOfCountry;
    }

    @NonNull
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_row, parent, false));
    }

    public int getItemCount() {
        return this.listOfCountry.size();
    }

    public void onBindViewHolder(@NonNull final ItemViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        String catCountryName = listOfCountry.get(position);
        if (catCountryName != null) {
            holder.origin.setText(catCountryName);
            holder.origin.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.putExtra(Constants.FILTER_KEY, catCountryName);
                ((AppCompatActivity) context).setResult(1000, intent);
                ((AppCompatActivity) context).finish();
            });
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView origin;

        ItemViewHolder(@NonNull View view) {
            super(view);
            origin = view.findViewById(R.id.tvFilter);
        }
    }

}

