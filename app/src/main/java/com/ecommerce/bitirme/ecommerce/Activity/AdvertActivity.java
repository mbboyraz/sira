package com.ecommerce.bitirme.ecommerce.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.bitirme.ecommerce.Classes.House;
import com.ecommerce.bitirme.ecommerce.Classes.OfferHouse;
import com.ecommerce.bitirme.ecommerce.Fragments.OfferFragment;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdvertActivity extends FragmentActivity implements ValueEventListener {

    Firebase mRef;
    Bundle bundle;
    House house;
    String usersid, lastoffer = "0", offeruserid;
    int i = 0;
    TextView fiyat, m2, tip, oda, kredi, aciklama, konum, aliciadi;
    FloatingActionButton fab;
    AlertDialog.Builder dialog;
    LinearLayout ilantipilay, odalay, sehirlay, kredilay, semtlay;
    TextView fiy, metre;
    EditText edt_fiyat, edtdelfiyat, edt_m2, edtdelm2, edt_aciklama;
    Button btndel;

    DateFormat current;
    Date currentTime;
    String currentDate;
    FragmentTransaction ft;
    android.app.Fragment offerfragment;

    boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evilan);

        bundle = getIntent().getExtras();
        offeruserid = bundle.getString("userid");


        Bundle bundle1 = new Bundle();
        bundle1.putString("ilanid", bundle.getString("id"));
        final OfferFragment offerfragment = new OfferFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, offerfragment);
        ft.commit();
        offerfragment.setArguments(bundle1);


        fiyat = findViewById(R.id.fiyataraligi);
        m2 = findViewById(R.id.m2araligi);
        tip = findViewById(R.id.emlaktipi);
        oda = findViewById(R.id.odasayisi);
        kredi = findViewById(R.id.krediuygun);
        aciklama = findViewById(R.id.ilanaciklama);
        konum = findViewById(R.id.ilankonum);
        aliciadi = findViewById(R.id.aliciadi);
        fab = findViewById(R.id.faballadvert);
        house = new House();
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);


        currentTime = new Date();
        current = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = current.format(currentTime);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new AlertDialog.Builder(AdvertActivity.this);
                View mview = getLayoutInflater().inflate(R.layout.activity_add_advert_house, null);
                ilantipilay = mview.findViewById(R.id.ilantipilay);
                semtlay = mview.findViewById(R.id.semtlay);
                odalay = mview.findViewById(R.id.odalay);
                sehirlay = mview.findViewById(R.id.sehirlay);
                kredilay = mview.findViewById(R.id.kredilay);
                ilantipilay.setVisibility(View.GONE);
                odalay.setVisibility(View.GONE);
                sehirlay.setVisibility(View.GONE);
                kredilay.setVisibility(View.GONE);
                semtlay.setVisibility(View.GONE);
                edt_fiyat = mview.findViewById(R.id.min_price_edttxt);
                edtdelfiyat = mview.findViewById(R.id.max_price_edttxt);
                edtdelm2 = mview.findViewById(R.id.sizes_max_edttxt);
                edt_m2 = mview.findViewById(R.id.sizes_min_edttxt);
                edt_aciklama = mview.findViewById(R.id.explanation_edttxt);
                btndel = mview.findViewById(R.id.save_advert_btn);
                fiy = mview.findViewById(R.id.price_txt);
                metre = mview.findViewById(R.id.sizes_txt);
                edtdelm2.setVisibility(View.GONE);
                edtdelfiyat.setVisibility(View.GONE);
                edt_aciklama.setHint("Teklif Açıklaması");
                btndel.setVisibility(View.GONE);
                fiy.setText("Teklif Fiyatı");
                metre.setText("m2");
                edt_m2.setHint("Teklif m2");
                edt_fiyat.setHint("Fiyat");
                dialog.setTitle("Teklif Yap");

                edt_fiyat.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
                fiy.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

                edt_m2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
                metre.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

                dialog.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (edt_m2.getText().toString().matches("") || edt_fiyat.getText().toString().matches("") || edt_aciklama.getText().toString().matches("")) {

                            Toast.makeText(getApplicationContext(), "Tüm Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_SHORT).show();
                        } else {

                            i = Integer.parseInt(lastoffer);
                            i++;
                            OfferHouse offerhouse = new OfferHouse(edt_fiyat.getText().toString(), edt_m2.getText().toString(), edt_aciklama.getText().toString(), currentDate.toString(), offeruserid);

                            mRef.child("teklifler").child(bundle.getString("id")).child(String.valueOf(i)).setValue(offerhouse);
                            mRef.child("users").child(offeruserid).child("tekliflerim").child("ev").child(bundle.getString("id")).setValue(String.valueOf(i));
                            mRef.child("teklifler").child(bundle.getString("id")).child("lastoffer").setValue(String.valueOf(i));

                            if (i >= 5) {
                                Toast.makeText(AdvertActivity.this, "Teklif Sınırına Ulaşıldı Teklif Veremezsiniz", Toast.LENGTH_LONG).show();
                                fab.setVisibility(View.GONE);
                            }
                        }


                    }
                });
                dialog.setNegativeButton("İptal", null);


                dialog.setView(mview);
                AlertDialog mdialog = dialog.create();
                mdialog.show();

            }
        });
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        try {
            usersid = dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getUserid();

            lastoffer = dataSnapshot.child("teklifler").child(bundle.getString("id")).child("lastoffer").getValue().toString();

            if (usersid.matches(offeruserid)) {
                fab.setVisibility(View.GONE);
            }

            if (isFirstTime && Integer.parseInt(lastoffer) >= 5) {
                fab.setVisibility(View.GONE);
                Toast.makeText(this, "Teklif Sınırına Ulaşıldı Teklif Veremezsiniz", Toast.LENGTH_LONG).show();
                isFirstTime = false;
            }
        } catch (Exception e) {

            mRef.child("teklifler").child(bundle.getString("id")).child("lastoffer").setValue(0);
            lastoffer = "0";
        }


        konum.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getSehir());
        fiyat.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMinFiyat() + "-" + dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMaxFiyat());
        m2.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMinM2() + "-" + dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMaxM2());
        tip.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanTipi());
        oda.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getOdaSayisi());
        aliciadi.setText(dataSnapshot.child("users").child(usersid).child("usersName").getValue().toString());
        String s = "Hayır";
        if (String.valueOf(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).krediyeUygun).equals("true")) {
            s = "Evet";

        }

        kredi.setText(s.toString());

        aciklama.setText("  İlan Açıklama:" + dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanAciklama());


        //  Toast.makeText(this, dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getSehir(), Toast.LENGTH_SHORT).show();
        setTitle(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanTipi());
  /*      if (usersid.matches(offeruserid) || Integer.parseInt(lastoffer) >= 5) {
            fab.setVisibility(View.GONE);
            if (Integer.parseInt(lastoffer) >= 5) {
                Toast.makeText(this, "Teklif Sınırına Ulaşıldı Teklif Veremezsiniz", Toast.LENGTH_LONG).show();
            }else{
                lastoffer = "0";
            }
        }else{
            lastoffer = "0";
        }*/
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }


}
