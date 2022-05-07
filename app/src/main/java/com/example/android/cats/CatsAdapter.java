package com.example.android.cats;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.ItemViewHolder> {
    ArrayList<Cat> listOfCats;

    public CatsAdapter(ArrayList<Cat> listOfCats) {
        this.listOfCats = listOfCats;
    }

    @NonNull
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cats_row, parent, false));
    }

    public int getItemCount() {
        return this.listOfCats.size();
    }

    public void onBindViewHolder(final ItemViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Cat cat = listOfCats.get(position);
        Picasso.get().load(cat.getImage().getUrl()).into(holder.image);
        holder.name.setText(cat.getName());
        holder.description.setText(cat.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), CatsDetailes.class);
            intent.putExtra("cat", listOfCats.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        ImageView image;
        TextView name;

        ItemViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.ivCat);
            name = view.findViewById(R.id.tvName);
            description = view.findViewById(R.id.tvDescription);
        }
    }
}


