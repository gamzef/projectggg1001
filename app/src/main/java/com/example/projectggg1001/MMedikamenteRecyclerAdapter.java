package com.example.projectggg1001;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MMedikamenteRecyclerAdapter extends RecyclerView.Adapter<MMedikamenteRecyclerAdapter.MedikamenteHolder> {

    private ArrayList<String> mnameList;
    private ArrayList<String> mzeitList;    //ilaç saati
    private ArrayList<Object> mwieoftList; //kaç saatte bir
    private ArrayList<String> menddatumList;
    private ArrayList<Object> mdosisList;
    private ArrayList<Object> mlagerList;

    public MMedikamenteRecyclerAdapter(ArrayList<String> mnameList, ArrayList<String> mzeitList, ArrayList<Object> mwieoftList, ArrayList<String> menddatumList, ArrayList<Object> mdosisList, ArrayList<Object> mlagerList) {
        this.mnameList = mnameList;
        this.mzeitList = mzeitList;
        this.mwieoftList = mwieoftList;
        this.menddatumList = menddatumList;
        this.mdosisList = mdosisList;
        this.mlagerList = mlagerList;
    }

    @NonNull
    @Override
    public MedikamenteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {      //ViwHolder oluşturulunca ne yapacağım?

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);

        return new MedikamenteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedikamenteHolder holder, int position) {     //ViewHolder'a bağlanınca ne yapacağım?
        holder.ilacismiText.setText(mnameList.get(position));
        holder.ilacsaatiText.setText(mzeitList.get(position));
        holder.ilaczamaniText.setText(""+mwieoftList.get(position));
        holder.ilactarihText.setText(menddatumList.get(position));
        holder.ilacdozuText.setText(""+mdosisList.get(position));
        holder.ilackapsulsayiText.setText(""+mlagerList.get(position));
    }

    @Override
    public int getItemCount() {     //kaç tane row var?
        return mnameList.size();
    }

    class MedikamenteHolder extends RecyclerView.ViewHolder{        //recyler_row'daki textview'ları burda tanımlıyoruz.

        TextView ilacismiText;
        TextView ilacsaatiText;
        TextView ilaczamaniText;
        TextView ilacdozuText;
        TextView ilackapsulsayiText;
        TextView ilactarihText;

        public MedikamenteHolder(@NonNull View itemView) {
            super(itemView);

            ilacismiText = itemView.findViewById(R.id.recyclerView_row_ilaçismi_text);
            ilacsaatiText = itemView.findViewById(R.id.recyclerView_row_ilaçsaati_text);
            ilaczamaniText = itemView.findViewById(R.id.recyclerView_row_ilaçzamanı_text);
            ilactarihText = itemView.findViewById(R.id.recyclerView_row_ilaçtarih_text);
            ilacdozuText = itemView.findViewById(R.id.recyclerView_row_ilaçdozu_text);
            ilackapsulsayiText = itemView.findViewById(R.id.recyclerView_row_ilaçkapsülsayısı_text);
        }
    }

}
