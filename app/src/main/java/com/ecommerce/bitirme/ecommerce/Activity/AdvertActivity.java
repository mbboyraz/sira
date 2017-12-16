package com.ecommerce.bitirme.ecommerce.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.Classes.House;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AdvertActivity extends AppCompatActivity implements ValueEventListener {

    Firebase mRef;
    Bundle bundle;
    House house;
    TextView fiyat, m2, tip, oda, kredi, aciklama, konum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evilan);
        bundle = getIntent().getExtras();

        fiyat = (TextView) findViewById(R.id.fiyataraligi);
        m2 = (TextView) findViewById(R.id.m2araligi);
        tip = (TextView) findViewById(R.id.emlaktipi);
        oda = (TextView) findViewById(R.id.odasayisi);
        kredi = (TextView) findViewById(R.id.krediuygun);
        aciklama = (TextView) findViewById(R.id.ilanaciklama);
        konum = (TextView) findViewById(R.id.ilankonum);

        house = new House();
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        konum.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getSehir());
        fiyat.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMinFiyat() + "-" + dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMaxFiyat());
        m2.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMinM2() + "-" + dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMaxM2());
        tip.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanTipi());
        oda.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getOdaSayisi());

        String s = "Hayır";
        if (String.valueOf(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).krediyeUygun).equals("true")) {
            s = "Evet";

        }

        kredi.setText(s.toString());

        aciklama.setText("  İlan Açıklama:" + dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanAciklama());



        //  Toast.makeText(this, dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getSehir(), Toast.LENGTH_SHORT).show();
        setTitle(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanTipi());
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
}
