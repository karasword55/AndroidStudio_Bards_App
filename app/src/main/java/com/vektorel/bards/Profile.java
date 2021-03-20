package com.vektorel.bards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    CollectionReference collectionReference;
    FirebaseFirestore db;
    ArrayList<String> data;
    ProfileAdapter profileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        String mail=(String) getIntent().getStringExtra("kisiemail");
        db=FirebaseFirestore.getInstance();
        collectionReference=db.collection("Posts");
        Query query=collectionReference.whereEqualTo("useremail",mail);
        data=new ArrayList<>();
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                       // Log.d(TAG, document.getId() + " => " + document.getData());
                        Log.e("1",document.getString("siir"));
                        if(document!=null){

                            data.add(document.getString("siir"));

                        }
                        RecyclerView recyclerView=findViewById(R.id.recyclerViewProfile);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Profile.this));
                        profileAdapter=new ProfileAdapter(data);
                        recyclerView.setAdapter(profileAdapter);
                        profileAdapter.notifyDataSetChanged();

                    }
                } else {
                    //Log.d(TAG, "Error getting documents: ", task.getException());
                    Log.e("erro9r",task.getException().toString());
                }

            }
        });







    }
}