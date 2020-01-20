package com.example.projectggg1001;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ArztterminActivity2 extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    EditText name_termin;
    Button bt_datepicker;
    Button bt_timepicker;
    TextView tv_zeittermin;
    TextView tv_datumtermin;
    EditText address_termin;
    Button bt_speicherntermin;
    private int hourofDay;
    private int hourofday1;
    private int minute1;
    int minutes;
    private String currentDateString;
    private FirebaseFirestore firebaseFirestore;
    private String nametermin;
    private String addresstermin;
    private String zeit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arzttermin2);

        firebaseFirestore = FirebaseFirestore.getInstance();
        name_termin = findViewById(R.id.terminname);
        bt_datepicker = findViewById(R.id.bt_termin_date);
        bt_timepicker = findViewById(R.id.bt_termin_zeit);
        tv_zeittermin = findViewById(R.id.tv_zeittermin);
        tv_datumtermin = findViewById(R.id.tv_datetermin);
        address_termin = findViewById(R.id.addresstermin);
        bt_speicherntermin = findViewById(R.id.speichern_termin);

        bt_timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        bt_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        bt_speicherntermin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nametermin = name_termin.getText().toString();
                addresstermin = address_termin.getText().toString();
                HashMap<String, Object> addTermin = new HashMap<>();
                addTermin.put("terminname",nametermin);
                addTermin.put("terminzeit",zeit);
                addTermin.put("termindatum",currentDateString);
                addTermin.put("terminaddress",addresstermin);
                //addTermin.put("alarm",);
                firebaseFirestore.collection("termine").add(addTermin).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intent23 = new Intent(ArztterminActivity2.this,MainActivity.class);
                        startActivity(intent23);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ArztterminActivity2.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourofDay = hourOfDay;
        minutes=minute;
        zeit = hourOfDay + ":" + minute;
        tv_zeittermin.setText(zeit);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        tv_datumtermin.setText(currentDateString);
    }


}