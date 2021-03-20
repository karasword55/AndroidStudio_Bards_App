package com.vektorel.bards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SiirlerRecyclerAdapter extends RecyclerView.Adapter<SiirlerRecyclerAdapter.SiirHolder> {

    private ArrayList<String> useremaillist;
    private ArrayList<String> usersiirlist;

    public SiirlerRecyclerAdapter(ArrayList<String> useremaillist, ArrayList<String> usersiirlist) {
        this.useremaillist = useremaillist;
        this.usersiirlist = usersiirlist;
    }

    @NonNull
    @Override
    public SiirHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_row_siirler,parent,false);

        return new SiirHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiirHolder holder, int position) {
        holder.txtemail.setText(useremaillist.get(position));
        holder.txtsiir.setText(usersiirlist.get(position));
    }

    @Override
    public int getItemCount() {
        return useremaillist.size();
    }

    public class SiirHolder extends RecyclerView.ViewHolder {
        TextView txtsiir,txtemail;

        public SiirHolder(@NonNull View itemView) {
            super(itemView);
            txtsiir=itemView.findViewById(R.id.recycler_row_siir);
            txtemail=itemView.findViewById(R.id.recycler_row_mail);

        }
    }
}
