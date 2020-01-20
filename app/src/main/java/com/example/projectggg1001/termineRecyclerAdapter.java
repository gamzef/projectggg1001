package com.example.projectggg1001;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class termineRecyclerAdapter extends RecyclerView.Adapter<termineRecyclerAdapter.termineHolder> {

    private ArrayList<String> tnameList;
    private ArrayList<String> tzeitList;
    private ArrayList<String> tdatumList;
    private ArrayList<String> taddressList;

    public termineRecyclerAdapter(ArrayList<String> tnameList, ArrayList<String> tzeitList,ArrayList<String> tdatumList, ArrayList<String> taddressList) {
        this.tnameList = tnameList;
        this.tzeitList = tzeitList;
        this.tdatumList = tdatumList;
        this.taddressList = taddressList;
    }


    @NonNull
    @Override
    public termineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {      //ViewHolder oluşturulunca ne yapacağım?

        //oluşturduğumuz xml dosyası ile bağlıyoruz(recycler_row_t)
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row_t,parent,false);

        return new termineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull termineHolder holder, int position) {     //ViewHolder'a bağlanınca ne yapacağım?
        holder.randevuismiText.setText(tnameList.get(position));
        holder.randevusaatiText.setText(tzeitList.get(position));
        holder.randevutarihiText.setText(tdatumList.get(position));
        holder.randevuadresiText.setText(taddressList.get(position));
    }

    @Override
    public int getItemCount() {     //kaç tane row var?
        return tnameList.size();
    }

    class termineHolder extends RecyclerView.ViewHolder{        //recyler_row'daki textview'ları burda tanımlıyoruz.

        TextView randevuismiText;
        TextView randevusaatiText;
        TextView randevutarihiText;
        TextView randevuadresiText;

        public termineHolder(@NonNull View itemView) {
            super(itemView);

            randevuismiText = itemView.findViewById(R.id.recyclerView_row_randevuismi_text);
            randevusaatiText = itemView.findViewById(R.id.recyclerView_row_randevusaati_text);
            randevutarihiText = itemView.findViewById(R.id.recyclerView_row_randevutarih_text);
            randevuadresiText = itemView.findViewById(R.id.recyclerView_row_randevuadresi_text);
        }
    }

}
