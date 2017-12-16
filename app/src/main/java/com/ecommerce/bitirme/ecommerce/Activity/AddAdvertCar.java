package com.ecommerce.bitirme.ecommerce.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AddAdvertCar extends AppCompatActivity {
    //values
    int i;
    String car_min_fiyat;
    String car_max_fiyat;
    String car_min_model;
    String car_max_model;
    String car_marka;
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
    //Network
    private Firebase mRef;
    //components
    private EditText edt_car_ilan_baslik;
    private EditText edt_car_fiyat_min;
    private EditText edt_car_fiyat_max;
    private EditText edt_car_model_min;
    private EditText edt_car_model_max;
    private EditText edt_car_marka;
    private Spinner spinner_car_sehir;
    private Spinner spinner_car_motorhacim;
    private Spinner spinner_car_yakit;
    private Spinner spinner_car_vites;
    private Spinner spinner_car_kasatip;
    private Spinner spinner_car_cekis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert_car);
        navi();
        initEvent();

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
    }

    private void degerleriAl() {
        car_marka = edt_car_marka.getText().toString();
        car_max_fiyat = edt_car_fiyat_max.getText().toString();
        car_min_fiyat = edt_car_fiyat_min.getText().toString();
        car_max_model = edt_car_model_max.getText().toString();
        car_min_model = edt_car_model_min.getText().toString();


    }

    private void navi() {
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
    }

}
