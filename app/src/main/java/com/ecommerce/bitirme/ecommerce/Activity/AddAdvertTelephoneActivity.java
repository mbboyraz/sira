package com.ecommerce.bitirme.ecommerce.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.bitirme.ecommerce.Classes.Telephone;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAdvertTelephoneActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    //components

    //values
    boolean kontrol;
    DateFormat current;
    Date currentTime;
    String currentDate;
    String telMarkaAl;
    String telModelAl;
    String telPriceMinAl;
    String telPriceMaxAl;
    String telDateAl;
    String telOSAl;
    String telRamAl = "";
    String telMemoryAl = "";
    String telCameraRearAl;
    String telCameraFrontAl;
    String telColorAl;
    String telStateAl;
    String telCityAl;
    String telAciklamaAl;
    String[] sehirler = new String[]{"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    String userId;
    String[] marka = {"Apple", "Asus", "Casper", "General Mobile", "Htc", "Huawei", "Kaan", "Lenovo", "Lg", "Motorola", "Nokia", "Samsung", "Sony", "Turkcell", "Vestel", "Xiomi"};
    String[] isletimSistemi = {"Android", "IOS", "Symbian", "Windows"};
    String[] renk = {"Altın Sarısı", "Gri", "Kırmızı", "Siyah", "Uzay Gri"};
    Bundle bundle;
    int i;
    private EditText edt_tel_model;
    private EditText edt_tel_price_min;
    private EditText edt_tel_price_max;
    private EditText edt_tel_rear_camera;
    private EditText edt_tel_front_camera;
    private EditText edt_tel_explanation;
    private TextView txt_bos_msg;
    private Spinner spn_tel_city;
    private Spinner spn_tel_color;
    private Spinner spn_tel_marka;
    private Spinner spn_tel_os;
    private CheckBox chk_tel_ram_1;
    private CheckBox chk_tel_ram_2;
    private CheckBox chk_tel_ram_3;
    private CheckBox chk_tel_ram_4;
    private CheckBox chk_tel_memory_16;
    private CheckBox chk_tel_memory_32;
    private CheckBox chk_tel_memory_64;
    private RadioButton radio_sifir, radio_ikinciel;
    private Button btn_save_advert;
    //server
    private Firebase mRef;
    //adapters
    private ArrayAdapter<String> sehirAdapter;
    private ArrayAdapter<String> markaAdapter;
    private ArrayAdapter<String> isletimAdapter;
    private ArrayAdapter<String> renkAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert_telephone);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("İlan Ver");

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


    private void navi() {
        //textview setting
        txt_bos_msg = (TextView) findViewById(R.id.tel_bos_alan);
        //edittext setting
        edt_tel_price_min = (EditText) findViewById(R.id.min_price_edttxt);
        edt_tel_price_max = (EditText) findViewById(R.id.max_price_edttxt);
        edt_tel_explanation = (EditText) findViewById(R.id.explanation_edttxt);
        edt_tel_rear_camera = (EditText) findViewById(R.id.tel_rear_edttxt);
        edt_tel_front_camera = (EditText) findViewById(R.id.tel_front_edttxt);
        edt_tel_model = (EditText) findViewById(R.id.model_edttxt);
        //spinner setting
        spn_tel_city = (Spinner) findViewById(R.id.city_spinner);
        spn_tel_marka = (Spinner) findViewById(R.id.marka_spinner);
        spn_tel_color = (Spinner) findViewById(R.id.color_spinner);
        spn_tel_os = (Spinner) findViewById(R.id.os_spinner);
        //radio button setting
        radio_sifir = (RadioButton) findViewById(R.id.sıfır_radio);
        radio_ikinciel = (RadioButton) findViewById(R.id.ikinci_radio);
        //chekbox setting
        chk_tel_memory_16 = (CheckBox) findViewById(R.id.gb16_checkbox);
        chk_tel_memory_32 = (CheckBox) findViewById(R.id.gb32_checkbox);
        chk_tel_memory_64 = (CheckBox) findViewById(R.id.gb64_checkbox);
        chk_tel_ram_1 = (CheckBox) findViewById(R.id.gb1_checkbox);
        chk_tel_ram_2 = (CheckBox) findViewById(R.id.gb2_checkbox);
        chk_tel_ram_3 = (CheckBox) findViewById(R.id.gb3_checkbox);
        chk_tel_ram_4 = (CheckBox) findViewById(R.id.gb4_checkbox);

        //buton setting
        btn_save_advert = (Button) findViewById(R.id.save_advert_btn);

        //adapter setting
        sehirAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sehirler);
        markaAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, marka);
        renkAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, renk);
        isletimAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, isletimSistemi);
        //spinner adapter setting
        spn_tel_os.setAdapter(isletimAdapter);
        spn_tel_color.setAdapter(renkAdapter);
        spn_tel_marka.setAdapter(markaAdapter);
        spn_tel_city.setAdapter(sehirAdapter);

    }

    private void initEvent() {
        btn_save_advert.setOnClickListener(this);
        spn_tel_city.setOnItemSelectedListener(this);
        spn_tel_marka.setOnItemSelectedListener(this);
        spn_tel_os.setOnItemSelectedListener(this);
        spn_tel_color.setOnItemSelectedListener(this);
        //checkbox listener
        chk_tel_ram_1.setOnCheckedChangeListener(this);
        chk_tel_ram_2.setOnCheckedChangeListener(this);
        chk_tel_ram_3.setOnCheckedChangeListener(this);
        chk_tel_ram_4.setOnCheckedChangeListener(this);
        chk_tel_memory_16.setOnCheckedChangeListener(this);
        chk_tel_memory_32.setOnCheckedChangeListener(this);
        chk_tel_memory_64.setOnCheckedChangeListener(this);
        radio_sifir.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        kontrol = false;
        degerleriAl();

        currentTime = new Date();
        current = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = current.format(currentTime);
        if (kontrol) {
            txt_bos_msg.setVisibility(View.INVISIBLE);
            firebaseTelEkle(telMarkaAl, telModelAl, telPriceMinAl, telPriceMaxAl, currentDate, telOSAl, telRamAl, telMemoryAl, telCameraRearAl, telCameraFrontAl, telColorAl, telStateAl, telCityAl, telAciklamaAl, userId);
            onBackPressed();
            Toast.makeText(this, "İlan başarıyla eklendi", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }

    }

    public void firebaseTelEkle(String marka, String model, String min, String max, String date, String os, String ram, String memory, String rear, String front, String color, String state, String city, String aciklama, String userid) {
        Telephone telephone = new Telephone(marka, model, min, max, date, os, ram, memory, rear, front, color, state, city, aciklama, userid);
        mRef.child("lastadvert").setValue(String.valueOf(i));
        mRef.child("ilanlar").child("telefon").child(String.valueOf(i)).setValue(telephone);
        mRef.child("users").child(userId).child("ilanlarım").child("telefon").child(String.valueOf(i)).setValue(String.valueOf(i));
    }

    private void degerleriAl() {
        telModelAl = edt_tel_model.getText().toString();
        telPriceMinAl = edt_tel_price_min.getText().toString();
        telPriceMaxAl = edt_tel_price_max.getText().toString();
        telCameraFrontAl = edt_tel_front_camera.getText().toString();
        telCameraRearAl = edt_tel_rear_camera.getText().toString();
        telAciklamaAl = edt_tel_explanation.getText().toString();
        if (radio_sifir.isChecked())
            telStateAl = "sıfır";
        else if (radio_ikinciel.isChecked())
            telStateAl = "ikinci el";
        if (telAciklamaAl.matches("") || telModelAl.matches("") || telPriceMaxAl.matches("") || telPriceMinAl.matches("") || telCameraRearAl.matches("") || telCameraFrontAl.matches("")) {
            txt_bos_msg.setVisibility(View.VISIBLE);
            txt_bos_msg.setText("Lütfen tüm alanları doldurunuz**");
            txt_bos_msg.setTextColor(Color.RED);
            kontrol = false;

        } else {
            kontrol = true;
        }
        userId = bundle.getString("usersid");
        i++;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.color_spinner:
                telColorAl = parent.getSelectedItem().toString();
                break;
            case R.id.city_spinner:
                telCityAl = parent.getSelectedItem().toString();
                break;
            case R.id.marka_spinner:
                telMarkaAl = parent.getSelectedItem().toString();
                if (parent.getSelectedItem().toString().matches("Apple")) {
                    spn_tel_os.setSelection(1);
                } else {
                    spn_tel_os.setSelection(0);
                }
                break;
            case R.id.os_spinner:
                telOSAl = parent.getSelectedItem().toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.gb1_checkbox:
                if (buttonView.isChecked())
                    telRamAl = telRamAl + "1GB";
                break;
            case R.id.gb2_checkbox:
                if (buttonView.isChecked())
                    telRamAl = telRamAl + ",2GB";
                break;
            case R.id.gb3_checkbox:
                if (buttonView.isChecked())
                    telRamAl = telRamAl + ",3GB";
                break;
            case R.id.gb4_checkbox:
                if (buttonView.isChecked())
                    telRamAl = telRamAl + ",4GB";
                break;
            case R.id.gb16_checkbox:
                if (buttonView.isChecked())
                    telMemoryAl = telMemoryAl + "16GB";
                break;
            case R.id.gb32_checkbox:
                if (buttonView.isChecked())
                    telMemoryAl = telMemoryAl + ",32GB";
                break;
            case R.id.gb64_checkbox:
                if (buttonView.isChecked())
                    telMemoryAl = telMemoryAl + ",64GB";
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
