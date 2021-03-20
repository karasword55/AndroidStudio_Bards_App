package com.vektorel.bards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SiirYaz extends AppCompatActivity {

    EditText txtsiir;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siir_yaz);
        txtsiir=findViewById(R.id.txtsiir);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();


    }
    public void siirpaylas(View view){
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        String mail=firebaseUser.getEmail();
        String siir=txtsiir.getText().toString();

        HashMap<String,Object> postdata=new HashMap<>();
        postdata.put("useremail",mail);
        postdata.put("siir",siir);
        postdata.put("date", FieldValue.serverTimestamp());

        firebaseFirestore.collection("Posts").add(postdata).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Intent intent=new Intent(SiirYaz.this,Anasayfa.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SiirYaz.this,e.getLocalizedMessage().toString(),
                        Toast.LENGTH_LONG).show();

            }
        });

    }
}