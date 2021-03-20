package com.vektorel.bards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.SiirProfileHolder> {

    private ArrayList<String> siirdata;

    public ProfileAdapter(ArrayList<String> siirdata) {
        this.siirdata = siirdata;
    }

    @NonNull
    @Override
    public SiirProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row_siirler_profile,parent,false);

        return new SiirProfileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiirProfileHolder holder, int position) {
        holder.txtsiir.setText(siirdata.get(position));
    }

    @Override
    public int getItemCount() {
        return siirdata.size();
    }

    public class SiirProfileHolder extends RecyclerView.ViewHolder {
        TextView txtsiir;
        public SiirProfileHolder(@NonNull View itemView) {
            super(itemView);
            txtsiir=itemView.findViewById(R.id.recycler_row_siir_profile);


        }
    }
}
