package com.example.adityapattani.gitfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Hands extends Fragment{
    ListView hands_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hands, container, false);
        final String[] hand_exercises = {"", "Pushups", "Punches", "Mountain Climbing", "Overhead Punches", "Thigh Taps", "Shoulder Taps"};
        hands_list = (ListView) v.findViewById(R.id.list_hands);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, hand_exercises);
        hands_list.setAdapter(adapter);

        hands_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data_pass = hand_exercises[position];
                Intent intent = new Intent(getActivity().getApplicationContext(), StartWorkout.class);
                intent.putExtra("Value", data_pass);
                startActivity(intent);
            }
        });
        return v;
    }
}
