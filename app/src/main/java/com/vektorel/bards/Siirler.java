package com.vektorel.bards;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class Siirler extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> useremailFromFB;
    ArrayList<String> usersiirFromFB;
    SiirlerRecyclerAdapter siirlerRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siirler);
        useremailFromFB=new ArrayList<>();
        usersiirFromFB=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        getDataFromFirestore();
        RecyclerView recyclerView=findViewById(R.id.recyclerViewsiirler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        siirlerRecyclerAdapter=new SiirlerRecyclerAdapter(useremailFromFB,usersiirFromFB);
        recyclerView.setAdapter(siirlerRecyclerAdapter);


    }
    public void getDataFromFirestore(){
        CollectionReference collectionReference=firebaseFirestore.collection("Posts");
        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(Siirler.this,error.getLocalizedMessage().toString(),
                            Toast.LENGTH_LONG).show();
                }
                if(value!=null){
                    for(DocumentSnapshot snapshot:value.getDocuments()){
                        Map<String,Object> data=snapshot.getData();
                        String siir=(String) data.get("siir");
                        String mail=(String) data.get("useremail");

                        useremailFromFB.add(mail);
                        usersiirFromFB.add(siir);
                        siirlerRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}