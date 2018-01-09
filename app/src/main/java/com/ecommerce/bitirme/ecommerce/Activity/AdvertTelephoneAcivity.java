package com.ecommerce.bitirme.ecommerce.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInstaller;
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

import com.ecommerce.bitirme.ecommerce.Classes.OfferTelephone;
import com.ecommerce.bitirme.ecommerce.Classes.Telephone;
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

public class AdvertTelephoneAcivity extends AppCompatActivity implements ValueEventListener {
    int i = 0, count = 0;
    boolean isFirstTime = true;
    PackageInstaller.Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    private Firebase mRef;
    private DateFormat current;
    private Date currentTime;
    private String currentDate;
    private Bundle bundle;
    private String sehirAl;
    private Telephone telephone;
    private String usersid, lastoffer, offeruserid, offerUsersTel;
    private String[] sehirler = new String[]{"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    private ArrayAdapter<String> sehirAdapter;
    private Button fab;
    private AlertDialog.Builder dialog;
    private Spinner spin_sehir;
    private EditText edt_fiyat, edt_model, edt_aciklama, edt_telnumber;
    private EditText offer_fiyat_edt, offer_model_edt, offer_tarih_edt, offer_aciklama_edt, offer_sehir_edt;
    private TextView offer_fiyat_txt, offer_model1_txt, offer_model2_txt, offer_tarih_txt, offer_aciklama_txt, offersAd, tel_txt, offer_sehir1_txt, offer_sehir2_txt;
    private ImageButton imgbtn_offers, imgbtn_offersAra;
    private ImageView imgview_adverts;
    private FragmentTransaction ft;
    private OfferFragment offerFragment;
    private TextView txt_fiyat_tel, txt_marka_tel, txt_model_tel,
            txt_os_tel, txt_ram_tel, txt_bellek_tel, txt_on_tel,
            txt_arka_tel, txt_durum_tel, txt_renk_tel, txt_konum_tel,
            txt_tarih_tel, txt_ilansahip_tel, txt_aciklama_tel;
    private String sendTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_telephone_acivity);
        navi();
        bundle = getIntent().getExtras();
        offeruserid = bundle.getString("userid");
        Bundle bundle1 = new Bundle();
        bundle1.putString("ilanid", bundle.getString("id"));
        bundle1.putString("gelenact", "Telefon");
        offerFragment = new OfferFragment();
        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, offerFragment);
        ft.commit();
        offerFragment.setArguments(bundle1);
        telephone = new Telephone();

        currentTime = new Date();
        current = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = current.format(currentTime);

        initEvent();
    }

    private void navi() {
        txt_fiyat_tel = (TextView) findViewById(R.id.tel_fiyataraligi_txt);
        txt_marka_tel = (TextView) findViewById(R.id.tel_marka_txt);
        txt_model_tel = (TextView) findViewById(R.id.tel_model_txt);
        txt_os_tel = (TextView) findViewById(R.id.tel_os_txt);
        txt_ram_tel = (TextView) findViewById(R.id.tel_ram_txt);
        txt_bellek_tel = (TextView) findViewById(R.id.tel_bellek_txt);
        txt_on_tel = (TextView) findViewById(R.id.tel_onkamera_txt);
        txt_arka_tel = (TextView) findViewById(R.id.tel_arkakamera_txt);
        txt_durum_tel = (TextView) findViewById(R.id.tel_durum_txt);
        txt_renk_tel = (TextView) findViewById(R.id.tel_renk_txt);
        txt_konum_tel = (TextView) findViewById(R.id.ilankonum_tel);
        txt_tarih_tel = (TextView) findViewById(R.id.tel_tarih_txt);
        txt_ilansahip_tel = (TextView) findViewById(R.id.aliciadi_tel);
        txt_aciklama_tel = (TextView) findViewById(R.id.tel_ilanaciklama_txt);
        imgview_adverts = (ImageView) findViewById(R.id.imgbtn_tel);
        fab = (Button) findViewById(R.id.faballadvert);
    }

    private void initEvent() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new AlertDialog.Builder(AdvertTelephoneAcivity.this);
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
                            OfferTelephone offerTelephone = new OfferTelephone(edt_fiyat.getText().toString(), edt_model.getText().toString(), sehirAl, edt_aciklama.getText().toString(), currentDate.toString(), offeruserid);

                            mRef.child("teklifler").child(bundle.getString("id")).child(String.valueOf(i)).setValue(offerTelephone);
                            mRef.child("users").child(offeruserid).child("tekliflerim").child("telefon").child(bundle.getString("id")).setValue(String.valueOf(i));
                            mRef.child("teklifler").child(bundle.getString("id")).child("lastoffer").setValue(String.valueOf(i));


                            Intent intentsend = new Intent(Intent.ACTION_SEND);


                            intentsend.putExtra(Intent.EXTRA_EMAIL, new String[]{sendTo});
                            intentsend.putExtra(Intent.EXTRA_SUBJECT, "İlanınıza Gelen Yeni Bir Teklif Yaptım!!!");
                            intentsend.putExtra(Intent.EXTRA_TEXT, "Merhabalar,\n Vermiş olduğunuz ilana yeni bir teklif yaptım.Teklif ayrıntıları aşağıda verilmiştir. Ayrıntılı bilgi için  http://www.my.sira.com/launch \n" +
                                    "Fiyat : " + edt_fiyat.getText().toString() + "\n Model : " + edt_model.getText().toString() + "\n Şehir : " + sehirAl + "\n Açıklama : " + edt_aciklama.getText().toString());

                            intentsend.setType("message/rfc822");

                            startActivity(Intent.createChooser(intentsend, "Select Email Sending App :"));
                            //startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailfrom:sira.ecommerce@gmail.com"+"mailto:"+sendTo)));
                            //bildirimYolla(edt_fiyat.getText().toString(),edt_m2.getText().toString(),edt_aciklama.getText().toString(),currentDate.toString(),offeruserid,bundle.getString("id"),i);

                            if (offerUsersTel.matches("")) {
                                mRef.child("users").child(offeruserid).child("usersTel").setValue(edt_telnumber.getText().toString());
                            }
                            if (count >= 6) {
                                Toast.makeText(AdvertTelephoneAcivity.this, "Teklif Sınırına Ulaşıldı Teklif Veremezsiniz", Toast.LENGTH_LONG).show();
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

    public AlertDialog.Builder tekliflerDialog(final String fiyat, final String model, final String aciklama, final String user, final String date, final String sehir, final String ilanid, final String teklifid, String offersname, String offersphoto, final String offerstel) {


        dialog = new AlertDialog.Builder(AdvertTelephoneAcivity.this);
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
                    mRef.child("teklifler").child(ilanid).removeValue();
                    OfferTelephone offerTelephone = new OfferTelephone(fiyat, model, sehir, aciklama, date, user);

                    mRef.child("teklifler").child(ilanid).child("100").setValue(offerTelephone);


                }
            });
            dialog.setNegativeButton("Reddet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //// TODO: 7.01.2018 sadece reddedilen teklif silinecek
                    mRef.child("teklifler").child(ilanid).child(teklifid).removeValue();
                    if (count == 2) {
                        mRef.child("teklifler").child(ilanid).removeValue();
                    }
                    mRef.child("users").child(offeruserid).child("tekliflerim").child("ev").child(ilanid).removeValue();
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
                    OfferTelephone offerTelephone = new OfferTelephone(offer_fiyat_edt.getText().toString(), offer_model_edt.getText().toString(), offer_sehir_edt.getText().toString(), offer_aciklama_edt.getText().toString(), offer_tarih_edt.getText().toString(), offeruserid);

                    mRef.child("teklifler").child(ilanid).child(teklifid).setValue(offerTelephone);

                }
            });
            dialog.setNegativeButton("Sil", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //// TODO: 7.01.2018 teklif firebaseden silinecek
                    mRef.child("teklifler").child(ilanid).child(teklifid).removeValue();
                    if (count == 2) {
                        mRef.child("teklifler").child(ilanid).removeValue();
                    }
                    mRef.child("users").child(offeruserid).child("tekliflerim").child("telefon").child(ilanid).removeValue();
                }
            });
            dialog.setNeutralButton("Kapat", null);

        } else {
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

            imgbtn_offersAra.setVisibility(View.GONE);

            dialog.setNeutralButton("Kapat", null);
        }

        dialog.setView(nview);
        AlertDialog mdialog = dialog.create();
        mdialog.show();
        return dialog;
    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        try {
            count = 0;
            usersid = dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getUserId();
            lastoffer = dataSnapshot.child("teklifler").child(bundle.getString("id")).child("lastoffer").getValue().toString();

            for (DataSnapshot gelen : dataSnapshot.child("teklifler").child(bundle.getString("id")).getChildren()) {
                count++;
            }

            if (usersid.matches(offeruserid)) {
                fab.setVisibility(View.GONE);
            }

            if (isFirstTime && count >= 6) {
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
        txt_konum_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelCity());
        txt_fiyat_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelPriceMin() + "-" + dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelPriceMax());
        txt_marka_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelMarka());
        txt_model_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelModel());
        txt_os_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelOS());
        txt_ram_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelRam());
        txt_bellek_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelMemory());
        txt_durum_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelState());
        txt_arka_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelCameraRear());
        txt_on_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelCameraFront());
        txt_renk_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelColor());
        txt_tarih_tel.setText(dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelDate());
        txt_ilansahip_tel.setText(dataSnapshot.child("users").child(usersid).child("usersName").getValue().toString());
        Picasso.with(this).load(dataSnapshot.child("users").child(usersid).child("usersPhotourl").getValue().toString()).into(imgview_adverts);
        sendTo = dataSnapshot.child("users").child(usersid).child("usersEmail").getValue().toString();

        txt_aciklama_tel.setText("İlan Açıklama:   " + dataSnapshot.child("ilanlar").child("telefon").child(bundle.getString("id")).getValue(Telephone.class).getTelAciklama().toString());

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
}
