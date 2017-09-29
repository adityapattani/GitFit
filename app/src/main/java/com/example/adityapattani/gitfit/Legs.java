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

public class Legs extends Fragment {
    ListView legs_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_legs, container, false);
        final String[] leg_exercises = {"", "Cycling", "Squats", "Low Kick", "High Kick", "Side Kick", "Round Kick", "Lunges"};
        legs_list = (ListView) v.findViewById(R.id.list_legs);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, leg_exercises);
        legs_list.setAdapter(adapter);

        legs_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data_pass = leg_exercises[position];
                Intent intent = new Intent(getActivity().getApplicationContext(), StartWorkout.class);
                intent.putExtra("Value", data_pass);
                startActivity(intent);
            }
        });
        return v;
    }
}
