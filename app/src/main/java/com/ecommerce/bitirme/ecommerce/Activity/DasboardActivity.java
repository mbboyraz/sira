package com.ecommerce.bitirme.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.R;
import com.squareup.picasso.Picasso;

public class DasboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView img_left_menu_photo;
    TextView txt_left_menu_user_name;
    TextView txt_left_menu_user_email;
    NavigationView nav_drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navi();
        Bundle extras = getIntent().getExtras();
        txt_left_menu_user_name.setText(extras.getString("name"));
        txt_left_menu_user_email.setText(extras.getString("email"));

        // Picasso.with(this).load(Uri.parse(extras.getString("photourl")).into(img_left_menu_photo);
        Picasso.with(DasboardActivity.this).load(extras.getString("photourl")).into(img_left_menu_photo);
//        img_left_menu_photo.setImageURI(Uri.parse(extras.getString("photourl")));
        //img_left_menu_photo.setImageURI(Uri.parse(extras.getString("photo")));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gec= new Intent(DasboardActivity.this,katagoriactivity.class);
                startActivity(gec);
              // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    public void navi() {

        txt_left_menu_user_email = (TextView) findViewById(R.id.left_menu_useremail_textview);
        txt_left_menu_user_name = (TextView) findViewById(R.id.left_menu_username_textview);
        img_left_menu_photo = (ImageView) findViewById(R.id.left_menu_profil_imageview);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dasboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent gec1= new Intent(this,AllAdvertsActivity.class);
        Intent gec2= new Intent(this,MyProfile.class);
        Intent gec3= new Intent(this,katagoriactivity.class);
        String sayfaad = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            sayfaad = "Ev_Ilani";
            gec1.putExtra("session", sayfaad);
            startActivity(gec1);
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {
            sayfaad = "Araba_Ilani";
            gec1.putExtra("session", sayfaad);
            startActivity(gec1);
        } else if (id == R.id.nav_slideshow) {
            sayfaad="Spor İlanları";
            gec1.putExtra("session", sayfaad);
            startActivity(gec1);
        } else if (id == R.id.nav_teknoloji) {
            sayfaad="Teknoloji İlanları";
            gec1.putExtra("session", sayfaad);
            startActivity(gec1);
        } else if (id == R.id.nav_tasit) {
            sayfaad="Taşıt İlanları";
            gec1.putExtra("session", sayfaad);
            startActivity(gec1);
        } else if (id == R.id.nav_share) {
            sayfaad="Share";
            gec1.putExtra("session", sayfaad);
            startActivity(gec1);
        } else if (id == R.id.nav_send) {
            sayfaad="Send";
            gec1.putExtra("session", sayfaad);
            startActivity(gec1);
        } else if (id == R.id.profil) {
            startActivity(gec2);
        }
        else if (id == R.id.ilanver) {
        startActivity(gec3);
        }



     /*   gec1.putExtra("session", sayfaad);
        startActivity(gec1);*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
