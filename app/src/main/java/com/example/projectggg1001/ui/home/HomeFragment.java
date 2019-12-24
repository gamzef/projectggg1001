package com.example.projectggg1001.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projectggg1001.MedspeichernActivity;
import com.example.projectggg1001.R;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button button_medspeichern = view.findViewById(R.id.button_mspeichern);
        button_medspeichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), MedspeichernActivity.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });
        return view;
    }

}