package com.ecommerce.bitirme.ecommerce.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ecommerce.bitirme.ecommerce.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

public class Giris extends AppCompatActivity implements View.OnClickListener {
    public Button btn_uyeliksiz;
    public SignInButton btn_googleSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        navi();

        btn_uyeliksiz.setOnClickListener(this);
      /*  GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();*/

    }
    public void navi(){
        btn_uyeliksiz = (Button) findViewById(R.id.uyeliksiz);
        btn_googleSignIn = (SignInButton) findViewById(R.id.signIn);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,DasboardActivity.class);
        startActivity(intent);
    }
}
