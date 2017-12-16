package com.ecommerce.bitirme.ecommerce.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.bitirme.ecommerce.Classes.Cars;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAdvertCar extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    //values
    DateFormat current;
    Date currentTime;
    String currentDate;

    int i;
    String car_min_fiyat;
    String car_max_fiyat;
    String car_min_model;
    String car_max_model;
    String car_marka;
    String car_baslik;
    String car_aciklama;
    String[] sehirler = new String[]{"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    String[] vites = new String[]{"Manuel", "Otomatik", "Yarı Otomatik"};
    String[] yakit = new String[]{"Dizel", "Benzin", "Benzin+LPG"};
    String[] kasatipi = new String[]{"Sedan", "Hatchack", "Coupe", "Cabrio"};
    String[] motorhacmi = new String[]{"1.2", "1.3", "1.4", "1.5", "1.6", "1.8", "2.0", "2.5 ve üstü"};
    String[] cekis = new String[]{"4x2 Önden", "4x2 Arkadan", "4x4"};
    String sehirAl, vitesAl, kasaAl, motorAl, cekisAl, yakitAl;
    String userId;
    Bundle bundle;
    boolean kontrol;
    //Network
    private Firebase mRef;
    //components
    private TextView txt_bos_hata;
    private Button btn_car_ilan_kaydet;
    private EditText edt_car_ilan_baslik;
    private EditText edt_car_fiyat_min;
    private EditText edt_car_fiyat_max;
    private EditText edt_car_model_min;
    private EditText edt_car_model_max;
    private EditText edt_car_marka;
    private EditText edt_car_aciklama;
    private Spinner spinner_car_sehir;
    private Spinner spinner_car_motorhacim;
    private Spinner spinner_car_yakit;
    private Spinner spinner_car_vites;
    private Spinner spinner_car_kasatip;
    private Spinner spinner_car_cekis;
    private ArrayAdapter<String> sehirAdapter;
    private ArrayAdapter<String> motorAdapter;
    private ArrayAdapter<String> yakitAdapter;
    private ArrayAdapter<String> vitesAdapter;
    private ArrayAdapter<String> kasatipAdapter;
    private ArrayAdapter<String> cekisAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert_car);
        navi();
        initEvent();
        bundle = getIntent().getExtras();

    }

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

    private void initEvent() {
        btn_car_ilan_kaydet.setOnClickListener(this);
        spinner_car_cekis.setOnItemSelectedListener(this);
        spinner_car_sehir.setOnItemSelectedListener(this);
        spinner_car_kasatip.setOnItemSelectedListener(this);
        spinner_car_motorhacim.setOnItemSelectedListener(this);
        spinner_car_vites.setOnItemSelectedListener(this);
        spinner_car_yakit.setOnItemSelectedListener(this);
    }

    private void degerleriAl() {
        car_marka = edt_car_marka.getText().toString();
        car_max_fiyat = edt_car_fiyat_max.getText().toString();
        car_min_fiyat = edt_car_fiyat_min.getText().toString();
        car_max_model = edt_car_model_max.getText().toString();
        car_min_model = edt_car_model_min.getText().toString();
        car_baslik = edt_car_ilan_baslik.getText().toString();
        car_aciklama = edt_car_aciklama.getText().toString();
        if (car_aciklama.matches("") || car_baslik.matches("") || car_min_model.matches("") || car_max_model.matches("") || car_min_fiyat.matches("") || car_max_fiyat.matches("") || car_marka.matches("")) {
            txt_bos_hata.setVisibility(View.VISIBLE);
            txt_bos_hata.setText("Lütfen tüm alanları doldurunuz**");
            txt_bos_hata.setTextColor(getColor(R.color.error));
            kontrol = false;
        } else {
            kontrol = true;
        }
        userId = bundle.getString("usersid");
        i++;
    }

    private void navi() {
        //component navigation
        btn_car_ilan_kaydet = (Button) findViewById(R.id.ilan_kaydet_car_btn);
        spinner_car_cekis = (Spinner) findViewById(R.id.cekis_car_spinner);
        spinner_car_sehir = (Spinner) findViewById(R.id.sehir_car_spinner);
        spinner_car_kasatip = (Spinner) findViewById(R.id.kasatip_car_spinner);
        spinner_car_motorhacim = (Spinner) findViewById(R.id.motor_hacim_car_spinner);
        spinner_car_vites = (Spinner) findViewById(R.id.vites_car_spinner);
        spinner_car_yakit = (Spinner) findViewById(R.id.yakit_car_spinner);
        edt_car_fiyat_max = (EditText) findViewById(R.id.fiyat_max_car_edt);
        edt_car_fiyat_min = (EditText) findViewById(R.id.fiyat_min_car_edt);
        edt_car_marka = (EditText) findViewById(R.id.marka_car_edt);
        edt_car_model_max = (EditText) findViewById(R.id.model_max_car_edt);
        edt_car_model_min = (EditText) findViewById(R.id.model_min_car_edt);
        edt_car_aciklama = (EditText) findViewById(R.id.ilan_aciklama_car_edt);
        edt_car_ilan_baslik = (EditText) findViewById(R.id.ilan_baslik_car_edt);
        txt_bos_hata = (TextView) findViewById(R.id.car_bos_alan);
        //spinner adapter navigation
        sehirAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sehirler);
        motorAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, motorhacmi);
        vitesAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, vites);
        yakitAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, yakit);
        kasatipAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, kasatipi);
        cekisAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, cekis);
        //spinner adapter setting
        spinner_car_yakit.setAdapter(yakitAdapter);
        spinner_car_vites.setAdapter(vitesAdapter);
        spinner_car_motorhacim.setAdapter(motorAdapter);
        spinner_car_kasatip.setAdapter(kasatipAdapter);
        spinner_car_sehir.setAdapter(sehirAdapter);
        spinner_car_cekis.setAdapter(cekisAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.cekis_car_spinner:
                cekisAl = parent.getSelectedItem().toString();
                break;
            case R.id.kasatip_car_spinner:
                kasaAl = parent.getSelectedItem().toString();
                break;
            case R.id.motor_hacim_car_spinner:
                motorAl = parent.getSelectedItem().toString();
                break;
            case R.id.sehir_car_spinner:
                sehirAl = parent.getSelectedItem().toString();
                break;
            case R.id.vites_car_spinner:
                vitesAl = parent.getSelectedItem().toString();
                break;
            case R.id.yakit_car_spinner:
                yakitAl = parent.getSelectedItem().toString();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {
        kontrol = false;
        degerleriAl();

        currentTime = new Date();
        current = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = current.format(currentTime);

        if (kontrol) {
            txt_bos_hata.setVisibility(View.INVISIBLE);
            firebaseCarEkle(car_max_model, car_min_model, car_max_fiyat, car_min_fiyat, car_baslik, car_aciklama, car_marka, sehirAl, yakitAl, vitesAl, kasaAl, cekisAl, motorAl, userId, currentDate.toString());
            onBackPressed();
            Toast.makeText(this, "İlan başarıyla eklendi", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else
            degerleriAl();
    }

    private void firebaseCarEkle(String modelMax, String modelMin, String fiyatMax, String fiyatMin, String baslik, String aciklama, String marka, String sehir, String yakit, String vites, String kasaTipi, String cekis, String motorHacmi, String userId, String date) {
        Cars car = new Cars(modelMax, modelMin, fiyatMax, fiyatMin, baslik, aciklama, marka, sehir, yakit, vites, kasaTipi, cekis, motorHacmi, userId, date);
        mRef.child("lastadvert").setValue(String.valueOf(i));
        mRef.child("ilanlar").child("araba").child(String.valueOf(i)).setValue(car);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
