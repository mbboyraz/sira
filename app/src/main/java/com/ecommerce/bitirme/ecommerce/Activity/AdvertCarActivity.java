package com.ecommerce.bitirme.ecommerce.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.bitirme.ecommerce.Classes.Cars;
import com.ecommerce.bitirme.ecommerce.Classes.House;
import com.ecommerce.bitirme.ecommerce.Classes.OfferCar;
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

public class AdvertCarActivity extends AppCompatActivity implements ValueEventListener {

    Firebase mRef;
    Bundle bundle;
    House house;
    String usersid, lastoffer, offeruserid, offerUsersTel;
    int i = 0;
    TextView fiyat, model, ilantip, marka, yakıt, vites, kasatipi, cekis, motorhacmi, aciklama, konum, aliciadi;
    Button fab;
    AlertDialog.Builder dialog;

    String[] sehirler = new String[]{"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};

    ArrayAdapter<String> sehirAdapter;
    String sehirAl;


    EditText edt_fiyat, edt_model, edt_aciklama, edt_telnumber;
    Spinner spin_sehir;

    EditText offer_fiyat_edt, offer_model_edt, offer_tarih_edt, offer_aciklama_edt, offer_sehir_edt;
    TextView offer_fiyat_txt, offer_model1_txt, offer_model2_txt, offer_tarih_txt, offer_aciklama_txt, offersAd, tel_txt, offer_sehir1_txt, offer_sehir2_txt;
    ImageButton imgbtn_offers, imgbtn_offersAra;
    ImageView imgview_adverts;


    DateFormat current;
    Date currentTime;
    String currentDate;
    FragmentTransaction ft;
    android.app.Fragment offerfragment;

    boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_car);

        bundle = getIntent().getExtras();
        offeruserid = bundle.getString("userid");


        Bundle bundle1 = new Bundle();
        bundle1.putString("ilanid", bundle.getString("id"));
        bundle1.putString("gelenact", "Car");

        final OfferFragment offerfragment = new OfferFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, offerfragment);
        ft.commit();
        offerfragment.setArguments(bundle1);

        fiyat = (TextView) findViewById(R.id.fiyataraligi_txt);
        model = (TextView) findViewById(R.id.modelaraligi_txt);
        ilantip = (TextView) findViewById(R.id.ilantipi_txt);
        marka = (TextView) findViewById(R.id.marka_txt);
        yakıt = (TextView) findViewById(R.id.yakıt_txt);
        vites = (TextView) findViewById(R.id.vites_txt);
        kasatipi = (TextView) findViewById(R.id.kasatipi_txt);
        cekis = (TextView) findViewById(R.id.cekis_txt);
        motorhacmi = (TextView) findViewById(R.id.motorhacmi_txt);
        aciklama = (TextView) findViewById(R.id.ilanaciklama);
        konum = (TextView) findViewById(R.id.ilankonum);
        aliciadi = (TextView) findViewById(R.id.aliciadi);
        fab = (Button) findViewById(R.id.faballadvert);
        imgview_adverts = (ImageView) findViewById(R.id.imgbtn);
        house = new House();
        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);


        currentTime = new Date();
        current = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = current.format(currentTime);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new AlertDialog.Builder(AdvertCarActivity.this);
                View mview = getLayoutInflater().inflate(R.layout.add_caroffer_dialog, null);

                edt_fiyat = mview.findViewById(R.id.price_edttxt);

                edt_model = mview.findViewById(R.id.models_edttxt);
                edt_aciklama = mview.findViewById(R.id.explanation_edttxt);
                edt_telnumber = mview.findViewById(R.id.tel_number_edttxt);
                sehirAdapter = new ArrayAdapter<String>(mview.getContext(), R.layout.support_simple_spinner_dropdown_item, sehirler);
                spin_sehir = mview.findViewById(R.id.sehir_spinner);
                spin_sehir.setAdapter(sehirAdapter);
                spin_sehir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        sehirAl = parent.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                tel_txt = mview.findViewById(R.id.tel_txt);

                if (!offerUsersTel.matches("")) {
                    edt_telnumber.setVisibility(View.GONE);
                    tel_txt.setVisibility(View.GONE);
                }


                dialog.setTitle("Teklif Yap");


                dialog.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (edt_model.getText().toString().matches("") || edt_fiyat.getText().toString().matches("") || edt_aciklama.getText().toString().matches("") || ((edt_telnumber.getVisibility() != View.GONE) && (edt_telnumber.getText().toString().matches(""))) || sehirAl.matches("")) {

                            Toast.makeText(getApplicationContext(), "Tüm Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_SHORT).show();
                        } else {

                            i = Integer.parseInt(lastoffer);
                            i++;
                            OfferCar offercar = new OfferCar(edt_fiyat.getText().toString(), edt_model.getText().toString(), sehirAl, edt_aciklama.getText().toString(), currentDate.toString(), offeruserid);

                            mRef.child("teklifler").child(bundle.getString("id")).child(String.valueOf(i)).setValue(offercar);
                            mRef.child("users").child(offeruserid).child("tekliflerim").child("araba").child(bundle.getString("id")).setValue(String.valueOf(i));
                            mRef.child("teklifler").child(bundle.getString("id")).child("lastoffer").setValue(String.valueOf(i));

                            //bildirimYolla(edt_fiyat.getText().toString(),edt_m2.getText().toString(),edt_aciklama.getText().toString(),currentDate.toString(),offeruserid,bundle.getString("id"),i);

                            if (offerUsersTel.matches("")) {
                                mRef.child("users").child(offeruserid).child("usersTel").setValue(edt_telnumber.getText().toString());
                            }
                            if (i >= 5) {
                                Toast.makeText(AdvertCarActivity.this, "Teklif Sınırına Ulaşıldı Teklif Veremezsiniz", Toast.LENGTH_LONG).show();
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
            usersid = dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getUserId();

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


        konum.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getSehir());
        fiyat.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getFiyatMin() + "-" + dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getFiyatMax());
        model.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getModelMin() + "-" + dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getModelMax());
        ilantip.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getBaslik());
        marka.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getMarka());
        yakıt.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getYakit());
        vites.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getVites());
        kasatipi.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getKasaTipi());
        cekis.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getCekis());
        motorhacmi.setText(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getMotorHacmi());

        aliciadi.setText(dataSnapshot.child("users").child(usersid).child("usersName").getValue().toString());
        Picasso.with(this).load(dataSnapshot.child("users").child(usersid).child("usersPhotourl").getValue().toString()).into(imgview_adverts);


        aciklama.setText("İlan Açıklama:   " + dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getAciklama().toString());


        //  Toast.makeText(this, dataSnapshot.child("ilanlar").child("ev").child(bundle.getString("id")).getValue(House.class).getSehir(), Toast.LENGTH_SHORT).show();
        // setTitle(dataSnapshot.child("ilanlar").child("araba").child(bundle.getString("id")).getValue(Cars.class).getBaslik());

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }

    public AlertDialog.Builder tekliflerDialog(String fiyat, String model, String aciklama, String user, String date, String sehir, String ilanid, String teklifid, String offersname, String offersphoto, final String offerstel) {


        dialog = new AlertDialog.Builder(AdvertCarActivity.this);
        View nview = getLayoutInflater().inflate(R.layout.show_offer_dialog, null);
        offer_fiyat_txt = nview.findViewById(R.id.fiyatbilgi_txt);
        offer_model1_txt = nview.findViewById(R.id.m2_txt);
        offer_model2_txt = nview.findViewById(R.id.m2bilgi_txt);
        offer_tarih_txt = nview.findViewById(R.id.tekliftarihi_txt);
        offer_aciklama_txt = nview.findViewById(R.id.aciklama_txt);
        offer_sehir1_txt = nview.findViewById(R.id.txt_sehircar1);
        offer_sehir2_txt = nview.findViewById(R.id.txt_sehircar2);

        offer_fiyat_edt = nview.findViewById(R.id.fiyataraligi_edt);
        offer_model_edt = nview.findViewById(R.id.m2aralig_edt);
        offer_tarih_edt = nview.findViewById(R.id.tekliftarihi_edt);
        offer_sehir_edt = nview.findViewById(R.id.edt_sehircar);
        offer_aciklama_edt = nview.findViewById(R.id.aciklama_edt);
        offersAd = nview.findViewById(R.id.teklifverenad_txt);
        imgbtn_offers = nview.findViewById(R.id.teklifveren_imgbtn);
        imgbtn_offersAra = nview.findViewById(R.id.offersArama_imgbtn);
        offersAd.setText(offersname);
        Picasso.with(nview.getContext()).load(offersphoto).into(imgbtn_offers);

        if (usersid.matches(offeruserid)) {
            offer_fiyat_txt.setText(fiyat);
            offer_model1_txt.setText("Model");
            offer_model2_txt.setText(model);
            offer_aciklama_txt.setText(aciklama);
            offer_tarih_txt.setText(date);
            offer_sehir2_txt.setText(sehir);


            offer_fiyat_edt.setVisibility(View.GONE);
            offer_tarih_edt.setVisibility(View.GONE);
            offer_model_edt.setVisibility(View.GONE);
            offer_sehir_edt.setVisibility(View.GONE);
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
            offer_model_edt.setText(model);
            offer_model1_txt.setText("Model");
            offer_tarih_edt.setText(currentDate);
            offer_sehir_edt.setText(sehir);
            offer_aciklama_edt.setText(aciklama);

            offer_tarih_edt.setEnabled(false);
            offer_fiyat_txt.setVisibility(View.GONE);
            offer_model2_txt.setVisibility(View.GONE);
            offer_tarih_txt.setVisibility(View.GONE);
            offer_sehir2_txt.setVisibility(View.GONE);
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

        }

        dialog.setView(nview);
        AlertDialog mdialog = dialog.create();
        mdialog.show();
        return dialog;
    }
}

