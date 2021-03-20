package com.vektorel.bards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Anasayfa extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void sairlerclick(View view){
        Intent intent=new Intent(Anasayfa.this,Sairler.class);
        startActivity(intent);
        //finish();
    }
    public void siirlerclick(View view){
        Intent intent=new Intent(Anasayfa.this,Siirler.class);
        startActivity(intent);
        //finish();
    }
    public void siiryazclick(View view){
        Intent intent=new Intent(Anasayfa.this,SiirYaz.class);
        startActivity(intent);
        //finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.anasayfamenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cikis){
            firebaseAuth.signOut();
            Intent intentToSignUp = new Intent(Anasayfa.this, MainActivity.class);
            startActivity(intentToSignUp);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}