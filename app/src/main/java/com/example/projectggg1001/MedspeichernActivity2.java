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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    private TextView et_medikamenteinfo2tv;
    private TextView et_medikamenteinfo3tv;
    private TextView et_medikamenteinfo4tv;
    private TextView et_medikamenteinfo5tv;
    private TextView et_medikamenteinfo6tv;
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
    private int id1;
    private int id2;
    private int id3;
    private int id4;
    private int id5;
    private int hourofDay;
    private int hourofday1;
    private int minute1;
    int minutes;
    private String zeit;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medspeichern2);

        firebaseFirestore = FirebaseFirestore.getInstance();
        et_medikamenteinfo1 = (TextView) findViewById(R.id.et_medikamenteinfo1tv);    //ZEİT
        et_medikamenteinfo2tv = (TextView) findViewById(R.id.et_medikamenteinfo2tv);  //ZEİT
        et_medikamenteinfo3tv = (TextView) findViewById(R.id.et_medikamenteinfo3tv);  //ZEİT
        et_medikamenteinfo4tv = (TextView) findViewById(R.id.et_medikamenteinfo4tv);  //ZEİT
        et_medikamenteinfo5tv = (TextView) findViewById(R.id.et_medikamenteinfo5tv);  //ZEİT
        et_medikamenteinfo6tv = (TextView) findViewById(R.id.et_medikamenteinfo6tv);  //ZEİT
        et_medikamenteinfo2 = (TextView) findViewById(R.id.et_medikamenteinfo2);    //DATUM
        et_medikamenteinfo3 = (EditText) findViewById(R.id.et_medikamenteinfo3);    //DOSİS
        et_medikamenteinfo4 = (EditText) findViewById(R.id.et_medikamenteinfo4);    //LAGER
        bt_medspeichern = (Button) findViewById(R.id.button_medspeichern);          //iLAÇ KAYDETME BUTONU

        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkbox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkbox6);
        checkBox7 = (CheckBox) findViewById(R.id.checkbox7);


        //önceki intentten ilaç ismi alınır.
        Intent intent = getIntent();
        final String medname = intent.getStringExtra("medname");

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    id1=4;
                    String basilacaksaatler=(hourofDay+":"+minutes);
                        for(int i = 0; i < id1;i++){
                            if(hourofDay+4<24) {
                                basilacaksaatler = insertString(basilacaksaatler, ", "+(hourofDay + 4) + ":" + minutes,0);
                                hourofDay=hourofDay+4;
                            }else{
                                hourofDay=hourofDay%24;
                                break;
                            }
                        }
                    et_medikamenteinfo1.setText(basilacaksaatler);
                }
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox2.isChecked()){
                    id1=8;  //kaç saatte bir saatlerin yazılacağı
                    String basilacaksaatler=(hourofDay+":"+minutes);
                    for(int i = 0; i < id1;i++){
                        if(hourofDay+8<24) {
                            basilacaksaatler = insertString(basilacaksaatler, ", "+(hourofDay + 8) + ":" + minutes,0);
                            hourofDay=hourofDay+8;
                        }else{
                            hourofDay=hourofDay%24;
                            break;
                        }
                    }
                    et_medikamenteinfo1.setText(basilacaksaatler);
                }
            }
        });

        //Bİ PROBLEM VAR BAK BAKALIM!!!!!!!!!!!!!!!!!!!!!!!éééééééééééééééééééééééééééééééééééé!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ééééééééééé!!!!!!!!!!!!!!!!!!!!
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    id1=12;
                    String basilacaksaatler=(hourofDay+":"+minutes);
                    for(int i = 0; i < id1;i++){
                        if(hourofDay+12<24) {
                            basilacaksaatler = insertString(basilacaksaatler, ", "+(hourofDay + 12) + ":" + minutes,0);
                            hourofDay=hourofDay+12;
                        }else{
                            hourofDay=hourofDay%24;
                            break;
                        }
                    }
                    et_medikamenteinfo1.setText(basilacaksaatler);
                }
            }
        });
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    id1=24;
                    String basilacaksaatler=(hourofDay+":"+minutes);
                    for(int i = 0; i < id1;i++){
                        if(hourofDay+24<24) {
                            basilacaksaatler = insertString(basilacaksaatler, ", "+(hourofDay + 24) + ":" + minutes,0);
                            hourofDay=hourofDay+24;
                        }else{
                            hourofDay=hourofDay%24;
                            break;
                        }
                    }
                    et_medikamenteinfo1.setText(basilacaksaatler);
                }
            }
        });
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    id1=48;
                    String basilacaksaatler=(hourofDay+":"+minutes);
                    for(int i = 0; i < id1;i++){
                        if(hourofDay+48<24) {
                            basilacaksaatler = insertString(basilacaksaatler, ", "+(hourofDay + 48) + ":" + minutes,0);
                            hourofDay=hourofDay+48;
                        }else{
                            hourofDay=hourofDay%24;
                            break;
                        }
                    }
                    et_medikamenteinfo1.setText(basilacaksaatler);
                }
            }
        });
        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    id1=7;
                    String basilacaksaatler=(hourofDay+":"+minutes);
                    et_medikamenteinfo1.setText(basilacaksaatler);
                }
            }
        });
        checkBox7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()){
                    id1=30;
                    String basilacaksaatler=(hourofDay+":"+minutes);
                    et_medikamenteinfo1.setText(basilacaksaatler);
                }
            }
        });
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

        bt_medspeichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medikamente_dosis = et_medikamenteinfo3.getText().toString();
                medikamente_lager = et_medikamenteinfo4.getText().toString();
                HashMap<String, Object> addMed1 = new HashMap<>();
                addMed1.put("medikamentename",medname);
                addMed1.put("medikamentezeit",hourofday1+":"+minute1);
                addMed1.put("medikamentewieoft",id1);
                addMed1.put("medikamenteenddatum",medikamente_zeit);
                addMed1.put("medikamentedosis",medikamente_dosis);
                addMed1.put("medikamentelager",medikamente_lager);
                firebaseFirestore.collection("medikamente").add(addMed1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intent = new Intent(MedspeichernActivity2.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MedspeichernActivity2.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    //Insert a String into another String
    public static String insertString(
            String originalString,
            String stringToBeInserted,
            int index)
    {
        index=originalString.length();
        // Create a new StringBuffer
        StringBuffer newString
                = new StringBuffer(originalString);

        // Insert the strings to be inserted
        // using insert() method
        newString.insert(index, stringToBeInserted);

        // return the modified String
        return newString.toString();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourofDay = hourOfDay;
        hourofday1 = hourOfDay;
        minute1 = minute;
        minutes=minute;
        et_medikamenteinfo1.setText(hourOfDay+":"+minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        et_medikamenteinfo2.setText(currentDateString);
        medikamente_zeit=currentDateString;
    }

}