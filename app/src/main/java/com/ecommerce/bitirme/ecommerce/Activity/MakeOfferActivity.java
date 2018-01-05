package com.ecommerce.bitirme.ecommerce.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ecommerce.bitirme.ecommerce.R;

public class MakeOfferActivity extends AppCompatActivity {
    String ilanId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_offer);
        Bundle extra = getIntent().getExtras();
        ilanId = extra.getString("ilanid");

    }
}
