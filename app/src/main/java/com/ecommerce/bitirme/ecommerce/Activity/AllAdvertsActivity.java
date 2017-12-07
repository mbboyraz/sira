package com.ecommerce.bitirme.ecommerce.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.bitirme.ecommerce.Activity.Classes.House;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AllAdvertsActivity extends AppCompatActivity implements ValueEventListener, View.OnClickListener {

    Firebase mRef;
    TextView txt, txt1;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        Bundle extras = getIntent().getExtras();
        if (extras.getString("session").matches("Ev_Ilani")) {
            setContentView(R.layout.evilan);
        } else if (extras.getString("session").matches("Araba_Ilani")) {
            setContentView(R.layout.arabailan);
        }
        txt = (TextView) findViewById(R.id.altbaslik);
        txt1 = (TextView) findViewById(R.id.baslik);
        btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(this);
        txt1.setText("Ev");
      /*  txt.setText(extras.getString("session"))   ;
setTitle(extras.getString("session"));*/

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        House house = new House();
        house.ilanAciklama = dataSnapshot.child("ilanlar").child("ev").child("3").child("ilanAciklama").getValue().toString();
        Toast.makeText(this, dataSnapshot.child("ilanlar").child("ev").child("3").child("ilanAciklama").getValue().toString(), Toast.LENGTH_SHORT).show();
//        for(DataSnapshot gelenler:dataSnapshot.child("ilanlar").child("ev").getChildren()){
//            Toast.makeText(this,gelenler.getValue(House.class).ilanAciklama+"\n",Toast.LENGTH_SHORT);
//        }
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }

    public void kayitlarigetir() {
        txt.setText("");
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);

    }

    @Override
    public void onClick(View v) {
        kayitlarigetir();
    }
}
