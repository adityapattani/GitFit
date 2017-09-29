package com.example.adityapattani.gitfit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Profile extends Fragment {

    TextView name, age, history;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        name = (TextView) v.findViewById(R.id.username);
        age = (TextView) v.findViewById(R.id.Age);
        history = (TextView) v.findViewById(R.id.workout_since);
        final DBHelper dbHelper = new DBHelper(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("Name", null);
        name.setText("Name: " + username);
        age.setText("Age: " + dbHelper.getAge(username));
        return v;
    }
}
