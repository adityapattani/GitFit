package com.example.adityapattani.gitfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class Exercise extends Fragment {
    GridView gridView;
    CustomAdapter adapter;

    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exercise, container, false);
        gridView = (GridView) v.findViewById(R.id.grid);
        int[] images = {R.drawable.hands, R.drawable.abs, R.drawable.legs};
        final String[] titles = {"Hands", "Abs", "Legs"};
        adapter = new CustomAdapter(getActivity().getApplicationContext(), R.layout.grid_element);
        gridView.setAdapter(adapter);

        int i = 0;
        while (i < titles.length){
            ExerciseDataProvider dataProvider = new ExerciseDataProvider(images[i], titles[i]);
            adapter.add(dataProvider);
            i++;
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Workouts.class);
                intent.putExtra("Name", titles[position]);
                startActivity(intent);
            }
        });

        return v;
    }


}
