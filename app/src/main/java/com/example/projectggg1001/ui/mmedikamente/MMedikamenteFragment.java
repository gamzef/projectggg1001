package com.example.projectggg1001.ui.mmedikamente;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectggg1001.MMedikamenteRecyclerAdapter;
import com.example.projectggg1001.R;
import com.firebase.client.Firebase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MMedikamenteFragment extends Fragment {

    private MMedikamenteViewModel MMedikamenteViewModel;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<String> mnameFromFB;
    private ArrayList<String> mzeitFromFB;
    private ArrayList<Object> mwieoftFromFB;
    private ArrayList<Object> mdosisFromFB;
    private ArrayList<Object> mlagerFromFB;
    private ArrayList<String> menddatumFromFB;
    MMedikamenteRecyclerAdapter mMedikamenteRecyclerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mmedikamente, container, false);

        FragmentActivity c = getActivity();
        firebaseFirestore = FirebaseFirestore.getInstance();

        mnameFromFB = new ArrayList<>();
        mzeitFromFB = new ArrayList<>();
        mwieoftFromFB = new ArrayList<>();
        menddatumFromFB = new ArrayList<>();
        mdosisFromFB = new ArrayList<>();
        mlagerFromFB = new ArrayList<>();

        getDataFromFirestore();

        //RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);      //recycler_row'la tek tek ekranda göstereceğimizi belirtiyoruz
        mMedikamenteRecyclerAdapter = new MMedikamenteRecyclerAdapter(mnameFromFB,mzeitFromFB,mwieoftFromFB,menddatumFromFB,mdosisFromFB,mlagerFromFB);
        recyclerView.setAdapter(mMedikamenteRecyclerAdapter);

        return root;
    }

    public void getDataFromFirestore(){
        CollectionReference collectionReference = firebaseFirestore.collection("medikamente");
        collectionReference.orderBy("medikamenteenddatum", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots!=null){
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                        Map<String,Object> mmedikamente = snapshot.getData();

                        //Casting
                        String medikamentename = (String) mmedikamente.get("medikamentename");
                        String medikamentezeit = (String) mmedikamente.get("medikamentezeit");
                        Object medikamentewieoft = (Object) mmedikamente.get("medikamentewieoft");
                        String medikamenteenddatum = (String) mmedikamente.get("medikamenteenddatum");
                        Object medikamentedosis = (Object) mmedikamente.get("medikamentedosis");
                        Object medikamentelager = (Object) mmedikamente.get("medikamentelager");

                        mnameFromFB.add(medikamentename);
                        mzeitFromFB.add(medikamentezeit);
                        mwieoftFromFB.add(medikamentewieoft);
                        menddatumFromFB.add(medikamenteenddatum);
                        mdosisFromFB.add(medikamentedosis);
                        mlagerFromFB.add(medikamentelager);

                        mMedikamenteRecyclerAdapter.notifyDataSetChanged();

                    }
                }
            }
        });
    }
}