package com.example.projectggg1001;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ArztterminActivity extends AppCompatActivity {
    private ArrayList<String> tnameFromFB;
    private ArrayList<String> tzeitFromFB;
    private ArrayList<String> tdatumFromFB;
    private ArrayList<String> taddressFromFB;
    private FirebaseFirestore firebaseFirestore;
    termineRecyclerAdapter termineRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arzttermin);

        getSupportActionBar().setTitle("Randevularım");

        firebaseFirestore = FirebaseFirestore.getInstance();

        tnameFromFB = new ArrayList<>();
        tzeitFromFB = new ArrayList<>();
        tdatumFromFB = new ArrayList<>();
        taddressFromFB = new ArrayList<>();

        getDataFromFirestore();

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));       //recycler_row'la tek tek ekranda göstereceğimizi belirtiyoruz
        termineRecyclerAdapter = new termineRecyclerAdapter(tnameFromFB,tzeitFromFB,tdatumFromFB,taddressFromFB);
        recyclerView.setAdapter(termineRecyclerAdapter);

    }
    public void getDataFromFirestore(){
        CollectionReference collectionReference = firebaseFirestore.collection("termine");
        collectionReference.orderBy("termindatum", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots!=null){
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                        Map<String,Object> termine = snapshot.getData();

                        //Casting
                        String terminname = (String) termine.get("terminname");
                        String terminzeit = (String) termine.get("terminzeit");
                        String termindatum = (String) termine.get("termindatum");
                        String terminaddress = (String) termine.get("terminaddress");

                        tnameFromFB.add(terminname);
                        tzeitFromFB.add(terminzeit);
                        tdatumFromFB.add(termindatum);
                        taddressFromFB.add(terminaddress);

                        termineRecyclerAdapter.notifyDataSetChanged();

                    }
                }
            }
        });
    }

}
