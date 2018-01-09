package com.ecommerce.bitirme.ecommerce.Activity;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdvertActivity extends FragmentActivity implements ValueEventListener, View.OnClickListener {

    Firebase mRef;
    Bundle bundle;
    House house;
    String usersid, lastoffer, offeruserid, offerUsersTel;
    int i = 0;
    TextView fiyat, m2, tip, oda, kredi, aciklama, konum, aliciadi, tel_txt;
    Button fab;
    AlertDialog.Builder dialog;

    LinearLayout sehirlay;

    EditText edt_fiyat, edt_m2, edt_aciklama, edt_telnumber;

    EditText offer_fiyat_edt, offer_m2_edt, offer_tarih_edt, offer_aciklama_edt;
    TextView offer_fiyat_txt, offer_m2_txt, offer_tarih_txt, offer_aciklama_txt, offersAd;
    ImageButton imgbtn_offers, imgbtn_offersAra;
    ImageView imgview_adverts;


    DateFormat current;
    Date currentTime;
    String currentDate;
    FragmentTransaction ft;
    android.app.Fragment offerfragment;

    boolean isFirstTime = true;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evilan);

        toolbar = findViewById(R.id.kategori_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setOnClickListener(this);
        bundle = getIntent().getExtras();
        offeruserid = bundle.getString("userid");


        Bundle bundle1 = new Bundle();
        bundle1.putString("ilanid", bundle.getString("id"));
        bundle1.putString("gelenact", "House");
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
        imgview_adverts = findViewById(R.id.imgbtn);
        house = new House();
        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);


        currentTime = new Date();
        current = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = current.format(currentTime);

        try {
            if (bundle.getString("flag").matches("notification")) {


                tekliflerDialog(bundle.getString("fiyat").toString(), bundle.getString("m2").toString(), bundle.getString("aciklama").toString(),
                        bundle.getString("userid").toString(), bundle.getString("date").toString(), bundle.getString("id").toString(),
                        bundle.getString("teklifId").toString(), "", "", "");
            }
        } catch (Exception e) {

        }



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new AlertDialog.Builder(AdvertActivity.this);
                View mview = getLayoutInflater().inflate(R.layout.add_offer_dialog, null);

                edt_fiyat = mview.findViewById(R.id.price_edttxt);

                edt_m2 = mview.findViewById(R.id.sizes_edttxt);
                edt_aciklama = mview.findViewById(R.id.explanation_edttxt);
                edt_telnumber = mview.findViewById(R.id.tel_number_edttxt);
                tel_txt = mview.findViewById(R.id.tel_txt);

                if (!offerUsersTel.matches("")) {
                    edt_telnumber.setVisibility(View.GONE);
                    tel_txt.setVisibility(View.GONE);
                }


                dialog.setTitle("Teklif Yap");


                dialog.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (edt_m2.getText().toString().matches("") || edt_fiyat.getText().toString().matches("") || edt_aciklama.getText().toString().matches("") || ((edt_telnumber.getVisibility() != View.GONE) && (edt_telnumber.getText().toString().matches("")))) {

                            Toast.makeText(getApplicationContext(), "Tüm Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_SHORT).show();
                        } else {

                            i = Integer.parseInt(lastoffer);
                            i++;
                            OfferHouse offerhouse = new OfferHouse(edt_fiyat.getText().toString(), edt_m2.getText().toString(), edt_aciklama.getText().toString(), currentDate.toString(), offeruserid);

                            mRef.child("teklifler").child(bundle.getString("id")).child(String.valueOf(i)).setValue(offerhouse);
                            mRef.child("users").child(offeruserid).child("tekliflerim").child("ev").child(bundle.getString("id")).setValue(String.valueOf(i));
                            mRef.child("teklifler").child(bundle.getString("id")).child("lastoffer").setValue(String.valueOf(i));

                            bildirimYolla(edt_fiyat.getText().toString(), edt_m2.getText().toString(), edt_aciklama.getText().toString(), currentDate.toString(), offeruserid, bundle.getString("id"), i);

                            if (offerUsersTel.matches("")) {
                                mRef.child("users").child(offeruserid).child("usersTel").setValue(edt_telnumber.getText().toString());
                            }
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
        try {
            offerUsersTel = dataSnapshot.child("users").child(offeruserid).child("usersTel").getValue().toString();
        } catch (Exception e) {

        }


        konum.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getSehir());
        fiyat.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMinFiyat() + "-" + dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMaxFiyat());
        m2.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMinM2() + "-" + dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getMaxM2());
        tip.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanTipi());
        oda.setText(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getOdaSayisi());
        aliciadi.setText(dataSnapshot.child("users").child(usersid).child("usersName").getValue().toString());
        Picasso.with(this).load(dataSnapshot.child("users").child(usersid).child("usersPhotourl").getValue().toString()).into(imgview_adverts);
        String s = "Hayır";
        if (String.valueOf(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).krediyeUygun).equals("true")) {
            s = "Evet";

        }

        kredi.setText(s.toString());

        aciklama.setText("İlan Açıklama:   " + dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanAciklama());


        //  Toast.makeText(this, dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getSehir(), Toast.LENGTH_SHORT).show();
        // setTitle(dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getIlanTipi());

    }


    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }

    public AlertDialog.Builder tekliflerDialog(String fiyat, String m2, String aciklama, String user, String date, String ilanid, String teklifid, String offersname, String offersphoto, final String offerstel) {


        dialog = new AlertDialog.Builder(AdvertActivity.this);
        View nview = getLayoutInflater().inflate(R.layout.show_offer_dialog, null);
        offer_fiyat_txt = nview.findViewById(R.id.fiyatbilgi_txt);
        offer_m2_txt = nview.findViewById(R.id.m2bilgi_txt);
        offer_tarih_txt = nview.findViewById(R.id.tekliftarihi_txt);
        offer_aciklama_txt = nview.findViewById(R.id.aciklama_txt);
        offer_fiyat_edt = nview.findViewById(R.id.fiyataraligi_edt);
        offer_m2_edt = nview.findViewById(R.id.m2aralig_edt);
        offer_tarih_edt = nview.findViewById(R.id.tekliftarihi_edt);
        offer_aciklama_edt = nview.findViewById(R.id.aciklama_edt);
        offersAd = nview.findViewById(R.id.teklifverenad_txt);
        imgbtn_offers = nview.findViewById(R.id.teklifveren_imgbtn);
        imgbtn_offersAra = nview.findViewById(R.id.offersArama_imgbtn);
        offersAd.setText(offersname);
        Picasso.with(nview.getContext()).load(offersphoto).into(imgbtn_offers);

        sehirlay = nview.findViewById(R.id.sehir_lay);
        sehirlay.setVisibility(View.GONE);


        if (usersid.matches(offeruserid)) {
            offer_fiyat_txt.setText(fiyat);
            offer_m2_txt.setText(m2);
            offer_aciklama_txt.setText(aciklama);
            offer_tarih_txt.setText(date);


            offer_fiyat_edt.setVisibility(View.GONE);
            offer_tarih_edt.setVisibility(View.GONE);
            offer_m2_edt.setVisibility(View.GONE);
            offer_aciklama_edt.setVisibility(View.GONE);

            imgbtn_offersAra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //// TODO: 7.01.2018 arama şeyleri olcak
                    Intent callintent = new Intent(Intent.ACTION_CALL);
                    callintent.setData(Uri.parse("tel:" + offerstel));
                    if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(callintent);
                }
            });

            dialog.setPositiveButton("Kabul Et", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //// TODO: 7.01.2018 kabul edilen haricindeki diğer teklifler silinecek


                }
            });
            dialog.setNegativeButton("Reddet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //// TODO: 7.01.2018 sadece reddedilen teklif silinecek
                }
            });
            dialog.setNeutralButton("Kapat", null);
        } else if (offeruserid.matches(user)) {

            offer_fiyat_edt.setText(fiyat);
            offer_m2_edt.setText(m2);
            offer_tarih_edt.setText(currentDate);
            offer_aciklama_edt.setText(aciklama);

            offer_tarih_edt.setEnabled(false);
            offer_fiyat_txt.setVisibility(View.GONE);
            offer_m2_txt.setVisibility(View.GONE);
            offer_tarih_txt.setVisibility(View.GONE);
            offer_aciklama_txt.setVisibility(View.GONE);

            imgbtn_offersAra.setVisibility(View.GONE);
            dialog.setPositiveButton("Düzenle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //// TODO: 7.01.2018 Verilen teklif bilgileri burada edt text de hazır getirilip düzenlenecek

                }
            });
            dialog.setNegativeButton("Sil", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //// TODO: 7.01.2018 teklif firebaseden silinecek
                }
            });
            dialog.setNeutralButton("Kapat", null);

        } else {
            offer_fiyat_txt.setText(fiyat);
            offer_m2_txt.setText(m2);
            offer_aciklama_txt.setText(aciklama);
            offer_tarih_txt.setText(date);


            offer_fiyat_edt.setVisibility(View.GONE);
            offer_tarih_edt.setVisibility(View.GONE);
            offer_m2_edt.setVisibility(View.GONE);
            offer_aciklama_edt.setVisibility(View.GONE);

            imgbtn_offersAra.setVisibility(View.GONE);

            dialog.setNeutralButton("Kapat", null);
        }


        dialog.setView(nview);
        AlertDialog mdialog = dialog.create();
        mdialog.show();
        return dialog;
    }

    public void bildirimYolla(String offerFiyat, String offerm2, String offeraciklma, String offerDate, String offersuserId, String offersilanId, int teklifId) {

        String s = "notification";
        NotificationManager noti = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, AdvertActivity.class);
        intent.putExtra("id", offersilanId);
        intent.putExtra("teklifId", teklifId);
        intent.putExtra("flag", s);
        intent.putExtra("fiyat", offerFiyat);
        intent.putExtra("m2", offerm2);
        intent.putExtra("aciklama", offeraciklma);
        intent.putExtra("date", offerDate);
        intent.putExtra("userid", offersuserId);
        PendingIntent launchIntent =
                PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notif = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_gavel_black_24dp)
                .setContentTitle("Yeni Bir Teklifiniz Var")
                .setContentText(offerFiyat)
                .setContentIntent(launchIntent)
                .build();
        notif.defaults |= Notification.DEFAULT_VIBRATE;
        notif.defaults |= Notification.DEFAULT_SOUND;

        noti.notify(0, notif);


    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
