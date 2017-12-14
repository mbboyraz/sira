package com.ecommerce.bitirme.ecommerce.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.Activity.Classes.House;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AdvertActivity extends AppCompatActivity implements ValueEventListener {

    Firebase mRef;
    Bundle bundle;
    House house;
    TextView sehiradi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);
        bundle = getIntent().getExtras();
        sehiradi = (TextView) findViewById(R.id.sehiradi);
        house = new House();
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        sehiradi.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getSehir());


        //  Toast.makeText(this, dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getSehir(), Toast.LENGTH_SHORT).show();
        setTitle(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanTipi());
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
}
