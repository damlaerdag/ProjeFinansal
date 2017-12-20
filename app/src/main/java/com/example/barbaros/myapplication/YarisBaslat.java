package com.example.barbaros.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class YarisBaslat extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    EditText mEt_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yaris_baslat);

        mEt_username=(EditText) findViewById(R.id.mEt_rakip);

        firebaseDatabase=FirebaseDatabase.getInstance();
    }
    public void rakipleEsles(View v)
    {
        downloadFromFirebase(mEt_username.getText().toString());
    }

    private void downloadFromFirebase(String username){
        DatabaseReference downRef = firebaseDatabase.getReference("Dictionary/Race/"+username);
        downRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap = (HashMap<String, String>) ds.getValue();

                    SendData(hashMap.get("Word").toString(),hashMap.get("Meaning").toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void SendData(String kelime, String anlam) {

        SQLiteDatabase db;
        Database data = new Database(this);

        if(ValueControl(kelime,anlam)) {
            data.InsertData( kelime,anlam,"Object");
            Toast toast = Toast.makeText(getApplicationContext(), "Kayıt İşlemi Başarılı", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, 250, 1500);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Sadece harf kullanabilrisiniz", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, 250, 250);
            toast.show();
        }

    }
    public boolean ValueControl(String edt1,String edt2)
    {
        boolean valuecontrol=true;
        for(int i=0;i<edt1.length();i++) {
            valuecontrol=false;
            if ((edt1.charAt(i) >= 'A' && edt1.charAt(i) <= 'Z')||(edt1.charAt(i) >= 'a' && edt1.charAt(i) <= 'z')) {
                valuecontrol = true;
            }
            else{
                return false;
            }
        }
        for(int i=0;i<edt2.length();i++) {
            valuecontrol=false;
            if ((edt2.charAt(i) >= 'A' && edt2.charAt(i) <= 'Z')||(edt2.charAt(i) >= 'a' && edt2.charAt(i) <= 'z')) {
                valuecontrol = true;
            }
            else
            {
                return false;
            }
        }

        return valuecontrol;
    }
}
