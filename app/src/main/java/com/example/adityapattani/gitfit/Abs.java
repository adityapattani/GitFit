package com.example.adityapattani.gitfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Abs extends Fragment {
    ListView abs_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_abs, container, false);
        final String[] ab_exercises = {"", "Crunches", "Heel Touch", "Leg Raises", "Half Plank", "Side Plank"};
        abs_list = (ListView) v.findViewById(R.id.list_abs);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ab_exercises);
        abs_list.setAdapter(adapter);

        abs_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data_pass = ab_exercises[position];
                Intent intent = new Intent(getActivity().getApplicationContext(), StartWorkout.class);
                intent.putExtra("Value", data_pass);
                startActivity(intent);
            }
        });
        return v;
    }
}
