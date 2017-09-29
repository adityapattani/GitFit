package com.example.adityapattani.gitfit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public CustomAdapter(Context applicationContext, int element) {
        super(applicationContext, element);
    }

    static class DataHandler {
        ImageView exercisePoster;
        TextView exerciseTitle;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View element;
        element = convertView;
        DataHandler handler = new DataHandler();

        if(convertView == null){
            LayoutInflater lI = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            element = lI.inflate(R.layout.grid_element,parent,false);
            handler.exercisePoster = (ImageView)element.findViewById(R.id.exe_img);
            handler.exerciseTitle = (TextView)element.findViewById(R.id.exe_name);
            element.setTag(handler);
        }
        else {
            handler = (DataHandler)element.getTag();
        }

        ExerciseDataProvider dataProvider;
        dataProvider = (ExerciseDataProvider) this.getItem(position);
        handler.exercisePoster.setImageResource(dataProvider.getExercise_poster());
        handler.exerciseTitle.setText(dataProvider.getExercise_title());
        return element;
    }

    public void add(Object object){
        super.add(object);
        list.add(object);
    }
}
