package com.example.project;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
class CategoryCardAdapter extends RecyclerView.Adapter<CategoryCardAdapter.ViewHolder> {

    private List<Category> categories;

    public CategoryCardAdapter(List<Category> categories) {
        this.categories = categories;
    }


    @NonNull
    @Override
    public CategoryCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_card,parent,false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView title = (TextView) cardView.findViewById(R.id.titleCategory);
        title.setText(categories.get(position).getName());
        TextView lesson1 = (TextView) cardView.findViewById(R.id.lesson1Title);
        lesson1.setText(categories.get(position).getLesson1());
        TextView lesson2 = (TextView) cardView.findViewById(R.id.lesson2Title);
        lesson2.setText(categories.get(position).getLesson2());
        TextView lesson3 = (TextView) cardView.findViewById(R.id.lesson3Title);
        lesson3.setText(categories.get(position).getLesson3());
        TextView lesson4 = (TextView) cardView.findViewById(R.id.lesson4Title);
        lesson4.setText(categories.get(position).getLesson4());
        ImageView lock = (ImageView) cardView.findViewById(R.id.lockSymbol);
        if(categories.get(position).getLock()==0) {
            lock.setImageResource(R.drawable.ic_baseline_lock_24);
        }
        else {
            lock.setImageResource(R.drawable.ic_baseline_lock_open_24);
        }

    }

    @Override
    public int getItemCount(){
       return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView view) {
            super(view);
            cardView = view;
        }

    }
}
