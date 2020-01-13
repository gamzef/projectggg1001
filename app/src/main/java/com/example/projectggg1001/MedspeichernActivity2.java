package com.example.projectggg1001;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MedspeichernActivity2 extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private Button bt_medspeichern;
    private TextView et_medikamenteinfo1;
    private TextView et_medikamenteinfo2;
    private EditText et_medikamenteinfo3;
    private EditText et_medikamenteinfo4;
    private EditText et_medikamenteinfo5;
    private EditText et_medikamenteinfo6;
    private String medikamente_zeit;
    private String medikamente_datum;
    private String medikamente_periode;
    private String medikamente_dosis;
    private String medikamente_wieoft;
    private String medikamente_lager;
    TimePickerDialog timePickerDialog;
    TextView tview; //silinecek

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medspeichern2);

        firebaseFirestore = FirebaseFirestore.getInstance();
        et_medikamenteinfo1 = (TextView) findViewById(R.id.et_medikamenteinfo1);
        et_medikamenteinfo2 = (TextView) findViewById(R.id.et_medikamenteinfo2);
        et_medikamenteinfo3 = (EditText) findViewById(R.id.et_medikamenteinfo3);
        et_medikamenteinfo4 = (EditText) findViewById(R.id.et_medikamenteinfo4);
        et_medikamenteinfo5 = (EditText) findViewById(R.id.et_medikamenteinfo5);
        et_medikamenteinfo6 = (EditText) findViewById(R.id.et_medikamenteinfo6);
        bt_medspeichern = (Button) findViewById(R.id.button_medspeichern);

        Button button = (Button) findViewById(R.id.medikamenteinfo1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

        Button button1 = (Button) findViewById(R.id.medikamenteinfo2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        //medikamente_zeit = et_medikamenteinfo1.getText().toString();
        //medikamente_datum = et_medikamenteinfo2.getText().toString();
        medikamente_periode = et_medikamenteinfo3.getText().toString();
        medikamente_dosis = et_medikamenteinfo4.getText().toString();
        medikamente_wieoft = et_medikamenteinfo5.getText().toString();
        medikamente_lager = et_medikamenteinfo6.getText().toString();

        //önceki intentten ilaç ismi alınır.
        Intent intent = getIntent();
        String medname = intent.getStringExtra("medname");

        //ÇALIŞMIYOR
        et_medikamenteinfo1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
                //ilackaydet(medikamente_zeit,medikamente_datum,medikamente_periode,medikamente_dosis,medikamente_wieoft,medikamente_lager);
                return false;
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        et_medikamenteinfo1.setText(hourOfDay + ":" + minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        et_medikamenteinfo2.setText(currentDateString);
    }

    private void ilackaydet(String et_medikamenteinfo1, String et_medikamenteinfo2, String et_medikamenteinfo3, String et_medikamenteinfo4, String et_medikamenteinfo5, String et_medikamenteinfo6){
        HashMap<String, Object> addMed1 = new HashMap<>();
        addMed1.put("medikamentezeit",et_medikamenteinfo1);
        addMed1.put("medikamenteendedatum",et_medikamenteinfo2);
        addMed1.put("medikamenteperiode",et_medikamenteinfo3);
        addMed1.put("medikamentedosis",et_medikamenteinfo4);
        addMed1.put("medikamentewieoft",et_medikamenteinfo5);
        addMed1.put("medikamentelager",et_medikamenteinfo6);

        firebaseFirestore.collection("medikamente").add(addMed1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MedspeichernActivity2.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
