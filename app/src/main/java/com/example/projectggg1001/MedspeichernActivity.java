package com.example.projectggg1001;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MedspeichernActivity extends AppCompatActivity {
    private EditText et_medikamente;
    private Button bt_medikamente;
    private String medikamente_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medspeichern);

        et_medikamente = (EditText) findViewById(R.id.et_medikamente);
        bt_medikamente = (Button) findViewById(R.id.button_medikamente);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bt_medikamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medikamente_name = et_medikamente.getText().toString();
                if(medikamente_name==""){
                    Toast.makeText(MedspeichernActivity.this,"Lütfen hatırlatıcı eklemek istediğiniz ilacın ismini giriniz.",Toast.LENGTH_LONG).show();
                }
                Intent intent2 = new Intent(MedspeichernActivity.this,MedspeichernActivity2.class);
                intent2.putExtra("medname",medikamente_name);
                startActivity(intent2);
                finish();
            }
        });
    }


}