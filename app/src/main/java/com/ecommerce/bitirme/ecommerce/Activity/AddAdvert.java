package com.ecommerce.bitirme.ecommerce.Activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.R;

public class AddAdvert extends AppCompatActivity {
    Spinner spinner;
   // TextView ilan;
    TextView ilanadi;
    String[] ilantipi = new String[]{"Satılık", "Kiralık", "Günlük Kiralık", "Devren Satılık"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert);
        ilanadi = (TextView) findViewById(R.id.ilanadi);
        Bundle extras = getIntent().getExtras();
        ilanadi.setText(extras.getString("session"));
        ilanadi.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        spinner = (Spinner) findViewById(R.id.advert_type_spinner);

       // ilan=(TextView) findViewById(R.id.ilan);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, ilantipi);
       // arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                 // ilan.setText(adapterView.getSelectedItem().toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}