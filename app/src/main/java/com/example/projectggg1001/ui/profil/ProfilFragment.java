package com.example.projectggg1001.ui.profil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectggg1001.LoginActivity;
import com.example.projectggg1001.MainActivity;
import com.example.projectggg1001.PasswortActivity;
import com.example.projectggg1001.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class ProfilFragment extends Fragment {

    private ProfilViewModel profilViewModel;
    private EditText nameText;
    private TextView emailText;
    private EditText geschlechtText;
    private EditText geburtsdatumText;
    private EditText gewichtText;
    private EditText grosseText;
    private EditText krankheitText;
    private TextView passwortText;
    private Button abbrechen;
    private Button speichern;
    private FirebaseUser firebaseUser;
    private String geschlecht;
    private String geburtsdatum;
    private String gewicht;
    private String grosse;
    private String krankheit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profilViewModel =
                ViewModelProviders.of(this).get(ProfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profil, container, false);


        MainActivity activity = (MainActivity) getActivity();
        String nameofuser = activity.getName();

        //Tanımlama
        nameText = (EditText) root.findViewById(R.id.nameText_pa);
        emailText = root.findViewById(R.id.emailText_pa);
        geschlechtText = root.findViewById(R.id.geschlechtText_pa);
        geburtsdatumText = root.findViewById(R.id.gdatumText_pa);
        gewichtText = root.findViewById(R.id.gewichtText_pa);
        grosseText = root.findViewById(R.id.grosseText_pa);
        krankheitText = root.findViewById(R.id.kranheitText_pa);
        passwortText = root.findViewById(R.id.tvPasswortLink);
        speichern = root.findViewById(R.id.btpa_speichern);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //Kullanıcının daha önce kaydettiği bilgileri Profil sayfasında gösterme
        nameText.setText(nameofuser);
        emailText.setText(firebaseUser.getEmail());
        geschlechtText.setText(geschlecht);
        geburtsdatumText.setText(geburtsdatum);
        gewichtText.setText(gewicht);
        grosseText.setText(grosse);
        krankheitText.setText(krankheit);

        //Şifre değiştirme
        passwortText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), PasswortActivity.class);
                startActivity(in);
            }
        });

        //Değiştirilen bilgileri kaydetme
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geschlecht=geschlechtText.getText().toString();
                geburtsdatum=geburtsdatumText.getText().toString();
                gewicht=gewichtText.getText().toString();
                grosse=grosseText.getText().toString();
                krankheit=krankheitText.getText().toString();
                updateProfile(nameText.getText().toString(),
                        emailText.getText().toString(),
                        geschlecht,
                        geburtsdatum,
                        gewicht,
                        grosse,
                        krankheit);
            }

        });
        return root;
    }

    private void updateProfile(String name, String email, String geschlecht, String geburtsdatum, String gewicht, String grosse, String krankheit) {
        //Emaili değiştirme
        firebaseUser.updateEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Profile Updated",Toast.LENGTH_LONG).show();
                        }
                    }
                });

        //Diğer bilgileri değiştirme
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        firebaseUser.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Profile Updated",Toast.LENGTH_LONG).show();

                        }
                    }
                });
        UserProfileChangeRequest profileUpdates2 = new UserProfileChangeRequest.Builder()
                .setDisplayName(geschlecht)
                .build();
        firebaseUser.updateProfile(profileUpdates2)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Profile Updated",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        UserProfileChangeRequest profileUpdates3 = new UserProfileChangeRequest.Builder()
                .setDisplayName(geburtsdatum)
                .build();
        firebaseUser.updateProfile(profileUpdates3)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Name updated",Toast.LENGTH_LONG).show();

                        }
                    }
                });
        UserProfileChangeRequest profileUpdates4 = new UserProfileChangeRequest.Builder()
                .setDisplayName(gewicht)
                .build();
        firebaseUser.updateProfile(profileUpdates4)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Profile Updated",Toast.LENGTH_LONG).show();

                        }
                    }
                });
        UserProfileChangeRequest profileUpdates5 = new UserProfileChangeRequest.Builder()
                .setDisplayName(grosse)
                .build();
        firebaseUser.updateProfile(profileUpdates5)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Profile Updated",Toast.LENGTH_LONG).show();

                        }
                    }
                });
        UserProfileChangeRequest profileUpdates6 = new UserProfileChangeRequest.Builder()
                .setDisplayName(krankheit)
                .build();
        firebaseUser.updateProfile(profileUpdates6)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Profile Updated",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}