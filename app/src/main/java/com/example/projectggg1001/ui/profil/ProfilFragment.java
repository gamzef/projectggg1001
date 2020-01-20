package com.example.projectggg1001.ui.profil;

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
import com.fasterxml.jackson.databind.util.BeanUtil;

public class ProfilFragment extends Fragment {

    private ProfilViewModel profilViewModel;
    private EditText nameText;
    private EditText emailText;
    private EditText geschlechtText;
    private EditText geburtsdatumText;
    private EditText gewichtText;
    private EditText grosseText;
    private EditText krankheitText;
    private TextView passwortText;
    private Button abbrechen;
    private Button speichern;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profilViewModel =
                ViewModelProviders.of(this).get(ProfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profil, container, false);

        //Tanımlama
        nameText = root.findViewById(R.id.nameText_pa);
        emailText = root.findViewById(R.id.emailText_pa);
        geschlechtText = root.findViewById(R.id.geschlechtText_pa);
        geburtsdatumText = root.findViewById(R.id.gdatumText_pa);
        gewichtText = root.findViewById(R.id.gewichtText_pa);
        grosseText = root.findViewById(R.id.grosseText_pa);
        krankheitText = root.findViewById(R.id.kranheitText_pa);
        passwortText = root.findViewById(R.id.tvPasswortLink);
        speichern = root.findViewById(R.id.btpa_speichern);

        //Şifre değiştirme
        passwortText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Değiştirilen bilgileri kaydetme
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return root;
    }
}