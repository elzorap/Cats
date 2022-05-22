package com.example.android.cats;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.ItemViewHolder> implements Filterable {
    ArrayList<Cat> listOfCats;
    ArrayList<Cat> listOfCatsFull;

    public CatsAdapter(ArrayList<Cat> listOfCats) {
        this.listOfCats = listOfCats;
        listOfCatsFull = new ArrayList<>(listOfCats);
    }

    @NonNull
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cats_row, parent, false));
    }

    public int getItemCount() {
        return this.listOfCats.size();
    }

    public void onBindViewHolder(@NonNull final ItemViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Cat cat = listOfCats.get(position);
        if (cat != null && cat.getImage() != null) {
            Picasso.get().load(cat.getImage().getUrl()).into(holder.image);
            holder.name.setText(cat.getName());
            holder.description.setText(cat.getDescription());
        }

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

    @Override
    public Filter getFilter() {
        return catsFilter;
    }

    private final Filter catsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Cat> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listOfCatsFull);
            } else {
                String filterPattern = constraint.toString().trim();

                for (Cat item : listOfCatsFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);

                    } else if (item.getName().toUpperCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listOfCats.clear();
            listOfCats.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}

