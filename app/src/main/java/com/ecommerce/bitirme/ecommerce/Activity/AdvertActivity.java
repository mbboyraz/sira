package com.ecommerce.bitirme.ecommerce.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ecommerce.bitirme.ecommerce.Activity.Classes.House;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;

public class AdvertActivity extends AppCompatActivity {
    DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);
        Bundle bundle = getIntent().getExtras();
        House house = new House();

        DataSnapshot ucanlar = dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id"));

        ucanlar.getValue(House.class);
        Toast.makeText(this, house.getSehir().toString(), Toast.LENGTH_SHORT).show();
    }
}
