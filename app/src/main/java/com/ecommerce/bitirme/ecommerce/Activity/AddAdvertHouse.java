package com.ecommerce.bitirme.ecommerce.Activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.bitirme.ecommerce.Classes.House;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAdvertHouse extends AppCompatActivity {

    DateFormat current;
    Date currentTime;
    String currentDate;
    Firebase mRef;
    Spinner spinner;
   // TextView ilan;
    TextView ilanadi;
    String[] ilantipi = new String[]{"Satılık", "Kiralık", "Günlük Kiralık", "Devren Satılık"};
    String[] odasayi = new String[]{"1+0", "1+1", "2+1", "2+2", "3+1", "3+2", "4+1", "4+2"};
    String[] sehirler = new String[]{"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    EditText minfiyat, maxfiyat;
    EditText minm2, maxm2;
    Spinner odasayisi, sehir, semt;
    RadioButton uygun, uygundegil;
    EditText aciklama;
    Button kaydet;
    int i;

    String sayi, tipi, sehiradi, ilanyazi;
    String minfiy, maxfiy, minmetre, maxmetre;
    boolean yes;

    String useremail, username, userid;

    Toolbar toolbar;


//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference();

    @Override
    protected void onStart() {
        super.onStart();
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String last = dataSnapshot.child("lastadvert").getValue().toString();
                i = Integer.parseInt(last);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert_house);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Firebase.setAndroidContext(this);
        degiskenHazirla();
        Bundle extras = getIntent().getExtras();
        ilanadi.setText(extras.getString("session"));
        ilanadi.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        username = extras.getString("username");
        useremail = extras.getString("useremail");
        userid = extras.getString("usersid");

       // ilan=(TextView) findViewById(R.id.ilan);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ilantipi);
       // arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                 // ilan.setText(adapterView.getSelectedItem().toString());
                tipi = adapterView.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sehirler);
        sehir.setAdapter(arrayAdapter2);
        sehir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sehiradi = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, odasayi);
        odasayisi.setAdapter(arrayAdapter3);
        odasayisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sayi = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        uygun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes = true;

            }
        });


        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilanyazi = aciklama.getText().toString();
                minfiy = minfiyat.getText().toString();
                maxfiy = maxfiyat.getText().toString();
                maxmetre = maxm2.getText().toString();
                minmetre = minm2.getText().toString();
                i++;

                currentTime = new Date();
                current = new SimpleDateFormat("dd.MM.yyyy");
                currentDate = current.format(currentTime);
                if (tipi.matches("") || sayi.matches("") || minmetre.matches("") || ilanyazi.matches("") || minfiy.matches("") || maxfiy.matches("")) {
                    Toast.makeText(v.getContext(), "Lütfen Tüm Alanları Doldurunuz", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    firebaseEvEkle(tipi, sayi, minmetre, maxmetre, yes, ilanyazi, sehiradi, minfiy, maxfiy, userid, currentDate);
                    onBackPressed();
                    Toast.makeText(AddAdvertHouse.this, "İlan başarıyla eklendi", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

            }
        });
    }

    public void degiskenHazirla() {
        ilanadi = (TextView) findViewById(R.id.ilanadi);
        spinner = (Spinner) findViewById(R.id.advert_type_spinner);
        minfiyat = (EditText) findViewById(R.id.min_price_edttxt);
        maxfiyat = (EditText) findViewById(R.id.max_price_edttxt);
        minm2 = (EditText) findViewById(R.id.sizes_min_edttxt);
        maxm2 = (EditText) findViewById(R.id.sizes_max_edttxt);
        odasayisi = (Spinner) findViewById(R.id.numberOf_spinner);
        sehir = (Spinner) findViewById(R.id.city_spinner);
        semt = (Spinner) findViewById(R.id.district_spinner);
        uygun = (RadioButton) findViewById(R.id.yes);
        uygundegil = (RadioButton) findViewById(R.id.no);
        aciklama = (EditText) findViewById(R.id.explanation_edttxt);
        kaydet = (Button) findViewById(R.id.save_advert_btn);
    }


    public void firebaseEvEkle(String tipi, String sayi, String minmetre, String maxmetre, boolean yes, String ilanyazi, String sehiradi, String minfiy, String maxfiy, String userid, String date) {


        House house = new House(tipi, sayi, minmetre, maxmetre, yes, ilanyazi, sehiradi, minfiy, maxfiy, userid, date);
        mRef.child("lastadvert").setValue(String.valueOf(i));
        mRef.child("ilanlar").child("ev").child(String.valueOf(i)).setValue(house);
        mRef.child("users").child(userid).child("ilanlarım").child("ev").child(String.valueOf(i)).setValue(String.valueOf(i));


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}