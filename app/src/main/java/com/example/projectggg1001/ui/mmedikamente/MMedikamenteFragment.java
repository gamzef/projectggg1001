package com.example.projectggg1001.ui.mmedikamente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

import java.util.Map;

public class MMedikamenteFragment extends Fragment {

    private MMedikamenteViewModel MMedikamenteViewModel;
    private FirebaseFirestore firebaseFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MMedikamenteViewModel =
                ViewModelProviders.of(this).get(MMedikamenteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mmedikamente, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        final TextView textView = root.findViewById(R.id.text_mmedikamente);
        MMedikamenteViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        getDataFromFirestore();
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
                        String medikamentename = (String) mmedikamente.get("medikamentename");
                        /*String medikamentezeit = (String) mmedikamente.get("medikamentezeit"+":"+"medikamenteminute");
                        int medikamentewieoft = (int) mmedikamente.get("medikamentewieoft");
                        int medikamentedosis = (int) mmedikamente.get("medikamentedosis");
                        int medikamentelager = (int) mmedikamente.get("medikamentelager");
                        String medikamenteenddatum = (String) mmedikamente.get("medikamenteenddatum");*/
                        System.out.println(medikamentename);
                    }
                }
            }
        });
    }
}