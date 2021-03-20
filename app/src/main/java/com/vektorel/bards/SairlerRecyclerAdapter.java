package com.vektorel.bards;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SairlerRecyclerAdapter  extends RecyclerView.Adapter<SairlerRecyclerAdapter.SairHolder> implements Filterable {


    private ArrayList<String> maillist;
    private ArrayList<String> tummaillist;
    private Context context;

    public SairlerRecyclerAdapter(ArrayList<String> maillist,Context context) {
        this.maillist = maillist;
        this.context=context;
        this.tummaillist=new ArrayList<>(maillist);

    }

    @NonNull
    @Override
    public SairHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.recycler_row_sairler,parent,false);

        return new SairHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SairHolder holder, int position) {
        holder.txtmail.setText(maillist.get(position));

    }

    @Override
    public int getItemCount() {
        return maillist.size();
    }

    @Override
    public Filter getFilter() {


        return filter;
    }

    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<String> filteredList=new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                filteredList.addAll(tummaillist);
            }else{
                //String filterpattern=constraint.toString().toLowerCase().trim();
                for(String maill:maillist){
                    if(maill.toLowerCase().contains(constraint.toString().toLowerCase().trim())){
                        filteredList.add(maill);


                    }else{
                        filteredList.addAll(tummaillist);
                    }
                }
            }

            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            maillist.clear();
            maillist.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class SairHolder extends RecyclerView.ViewHolder {

        TextView txtmail;

        public SairHolder(@NonNull View itemView) {
            super(itemView);
            txtmail=itemView.findViewById(R.id.recycler_row_sairler_mail);
            String mail=txtmail.toString();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,Profile.class);
                    intent.putExtra("kisiemail",maillist.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });




        }
    }
}

