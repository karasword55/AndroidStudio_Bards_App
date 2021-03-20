package com.vektorel.bards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText txtsifre,txtmail;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    int[] colors={Color.RED};
    Button btnkayit,btngiris;
    TextView txtbardsbaslik;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtbardsbaslik=findViewById(R.id.textView5);
        btnkayit=findViewById(R.id.btnkayit);
        btngiris=findViewById(R.id.btngiris);
        txtsifre=findViewById(R.id.txtsifre);
        txtmail=findViewById(R.id.txtmail);
        firebaseAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            Intent intent=new Intent(MainActivity.this,Anasayfa.class);
            startActivity(intent);
            finish();
        }


    }
    public void kayit(View view){
        btnkayit.setBackgroundColor(colors[0]);
        startanimation();
        String mail=txtmail.getText().toString();
        String sifre=txtsifre.getText().toString();
        HashMap<String,Object> maildata=new HashMap<>();
        maildata.put("mail",mail);
        maildata.put("date", FieldValue.serverTimestamp());
        db.collection("Kullanıcı Mailleri").add(maildata).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });


        firebaseAuth.createUserWithEmailAndPassword(mail,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this,"kullanıcı başarıyla kaydedildi!",
                        Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,Anasayfa.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });




    }
    public void giris(View view){
        btngiris.setBackgroundColor(colors[0]);
        startanimation();
        String mail=txtmail.getText().toString();
        String sifre=txtsifre.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(mail,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent=new Intent(MainActivity.this,Anasayfa.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    public void startanimation(){
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim);
        txtbardsbaslik.startAnimation(animation);
    }
}