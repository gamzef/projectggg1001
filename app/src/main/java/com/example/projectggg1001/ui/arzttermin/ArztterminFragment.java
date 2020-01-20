package com.example.projectggg1001.ui.arzttermin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectggg1001.ArztterminActivity;
import com.example.projectggg1001.ArztterminActivity2;
import com.example.projectggg1001.R;

public class ArztterminFragment extends Fragment {

    private ArztterminViewModel arztterminViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        arztterminViewModel = ViewModelProviders.of(this).get(ArztterminViewModel.class);
        View root = inflater.inflate(R.layout.fragment_arzttermin, container, false);

        Button bt_zeigtermine = root.findViewById(R.id.bt_zeigtermin);
        Button bt_hinzufügtermine = root.findViewById(R.id.bt_hinzufügtermin);

        bt_zeigtermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ArztterminActivity.class);
                startActivity(in);
            }
        });
        bt_hinzufügtermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(getActivity(), ArztterminActivity2.class);
                startActivity(in2);
            }
        });
        return root;
    }
}