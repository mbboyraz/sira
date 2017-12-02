package com.ecommerce.bitirme.ecommerce.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.R;

public class AllAdvertsActivity extends AppCompatActivity {

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras.getString("session").matches("Ev_Ilani")) {
            setContentView(R.layout.evilan);
        } else if (extras.getString("session").matches("Araba_Ilani")) {
            setContentView(R.layout.arabailan);
        }
        txt = (TextView) findViewById(R.id.txt);
      /*  txt.setText(extras.getString("session"))   ;
setTitle(extras.getString("session"));*/

    }
}
