package com.example.project;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


class LessonPageAdapter extends RecyclerView.Adapter<LessonPageAdapter.ViewHolder>{

    private ArrayList<LessonPage> pages;

    public LessonPageAdapter(ArrayList<LessonPage> pages) {
        this.pages = pages;
    }

    @NonNull
    @Override
    public LessonPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lessonView = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_lesson_page,parent,false);
        return new ViewHolder(lessonView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        View lessonView = holder.itemView;
        int pictureCheck = pages.get(position).getImageId();
        ImageView imageView = (ImageView) lessonView.findViewById(R.id.lessonImage);
        imageView.setImageResource(android.R.color.transparent);
        if(!(pictureCheck == 0)) {
            Drawable curImage = ContextCompat.getDrawable(lessonView.getContext(),pages.get(position).getImageId());
            imageView.setImageDrawable(curImage);
        }
        TextView textView = (TextView) lessonView.findViewById(R.id.lessonText);
        textView.setText(pages.get(position).getStringId());

    }

    @Override
    public int getItemCount() {
        return pages.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Define the view to be used for each page
        private View lessonView;

        public ViewHolder(View v) {
            super(v);
            lessonView = v;

        }
    }
}