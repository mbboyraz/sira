package com.ecommerce.bitirme.ecommerce.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.Activity.Classes.House;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllAdvertsActivity extends AppCompatActivity implements ValueEventListener, View.OnClickListener {

    final List<katagori> ilanlar = new ArrayList<katagori>();
    Firebase mRef;
    TextView txt, txt1;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);
        Bundle extras = getIntent().getExtras();
        //  if (extras.getString("session").matches("Ev_Ilani")) {
        setContentView(R.layout.activity_all_adverts);
      /*  } else if (extras.getString("session").matches("Araba_Ilani")) {
            setContentView(R.layout.arabailan);
        }*/
        //  txt = (TextView) findViewById(R.id.altbaslik);
        //   txt1 = (TextView) findViewById(R.id.baslik);
        //  btn = (Button) findViewById(R.id.button2);
        //  btn.setOnClickListener(this);
        //  txt1.setText("Ev");
      /*  txt.setText(extras.getString("session"))   ;
setTitle(extras.getString("session"));*/

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        final ListView ilanlarliste = (ListView) findViewById(R.id.ilanlarliste);
        House house = new House();
        //house.ilanAciklama = dataSnapshot.child("ilanlar").child("ev").child("3").child("ilanAciklama").getValue().toString();
        //     Toast.makeText(this, dataSnapshot.child("ilanlar").child("ev").child("14").child("ilanAciklama").getValue().toString(), Toast.LENGTH_SHORT).show();
//        for(DataSnapshot gelenler:dataSnapshot.child("ilanlar").child("ev").getChildren()){
//            Toast.makeText(this,gelenler.getValue(House.class).ilanAciklama+"\n",Toast.LENGTH_SHORT);
//        }
        for (DataSnapshot gelenler : dataSnapshot.child("ilanlar").child("ev").getChildren()) {
            ilanlar.add(new katagori("Ev",
                    gelenler.getValue(House.class).getSehir() + " , " + gelenler.getValue(House.class).getIlanTipi() + "->" + gelenler.getValue(House.class).getOdaSayisi()));
        }

        adapter ilanadapter = new adapter(this, ilanlar);
        ilanlarliste.setAdapter(ilanadapter);
        ilanlarliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }

    public void kayitlarigetir() {
        txt.setText("");


    }

    @Override
    public void onClick(View v) {
        kayitlarigetir();
    }
}
