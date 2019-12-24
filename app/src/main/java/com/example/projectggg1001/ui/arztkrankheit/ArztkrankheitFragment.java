package com.example.projectggg1001.ui.arztkrankheit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectggg1001.R;

public class ArztkrankheitFragment extends Fragment {

    private ArztkrankheitViewModel arztkrankheitViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        arztkrankheitViewModel =
                ViewModelProviders.of(this).get(ArztkrankheitViewModel.class);
        View root = inflater.inflate(R.layout.fragment_arztkrankheit, container, false);
        final TextView textView = root.findViewById(R.id.text_arztkrankheit);
        arztkrankheitViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}