package com.example.projectggg1001.ui.arztkrankheit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectggg1001.R;

import org.w3c.dom.Text;

public class ArztkrankheitFragment extends Fragment {

    private ArztkrankheitViewModel arztkrankheitViewModel;
    private EditText teilname;
    private EditText beschwerdename;
    private TextView namearzt;
    private Button ergebnis;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        arztkrankheitViewModel =
                ViewModelProviders.of(this).get(ArztkrankheitViewModel.class);
        View root = inflater.inflate(R.layout.fragment_arztkrankheit, container, false);

        teilname = root.findViewById(R.id.teilname);
        beschwerdename = root.findViewById(R.id.beschwerdename);
        namearzt = root.findViewById(R.id.namearzt);
        ergebnis = root.findViewById(R.id.ergebnis);

        //Kullanıcının hangi doktora gideceğini gösterecek.
        ergebnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_teil = teilname.getText().toString().toLowerCase();
                String name_beschwerde = beschwerdename.getText().toString().toLowerCase();

            }
        });

        return root;
    }
}