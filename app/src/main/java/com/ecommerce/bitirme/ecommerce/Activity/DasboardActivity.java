package com.ecommerce.bitirme.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.Classes.House;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DasboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ValueEventListener {
    ImageView img_left_menu_photo;
    TextView txt_left_menu_user_name;
    TextView txt_left_menu_user_email;
    NavigationView nav_drawer;
    boolean isFirst = false;
    String photourl, username, useremail, userid;

    ListView list_dashboardEv, list_dashboardAraba;

    Firebase mRef;
    List<katagori> Evilanlar = new ArrayList<katagori>();
    List<katagori> Arabailanlar = new ArrayList<katagori>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navi();
        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);
        Bundle extras = getIntent().getExtras();
        username = extras.getString("name");
        txt_left_menu_user_name.setText(username);
        useremail = extras.getString("email");
        txt_left_menu_user_email.setText(useremail);

        userid = extras.getString("usersid");
        photourl = extras.getString("photourl");
        // Picasso.with(this).load(Uri.parse(extras.getString("photourl")).into(img_left_menu_photo);
        Picasso.with(DasboardActivity.this).load(photourl).into(img_left_menu_photo);
//        img_left_menu_photo.setImageURI(Uri.parse(extras.getString("photourl")));
        //img_left_menu_photo.setImageURI(Uri.parse(extras.getString("photo")));
        Button fab = (Button) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gec= new Intent(DasboardActivity.this,katagoriactivity.class);
                gec.putExtra("useremail", useremail);
                gec.putExtra("username", username);

                gec.putExtra("usersid", userid);
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
            if (isFirst)
                finish();
            else {
                isFirst = true;
                onBackPressed();
                super.onBackPressed();
            }


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
            gec1.putExtra("usersid", userid);
            startActivity(gec1);
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {
            sayfaad = "Araba_Ilani";
            gec1.putExtra("session", sayfaad);
            gec1.putExtra("usersid", userid);
            startActivity(gec1);
        } else if (id == R.id.nav_teknoloji) {
            sayfaad = "Telefon_Ilani";
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
            gec2.putExtra("profilephoto", photourl);
            gec2.putExtra("useremail", useremail);
            gec2.putExtra("username", username);
            gec2.putExtra("usersid", userid);
            startActivity(gec2);
        }
        else if (id == R.id.ilanver) {
            gec3.putExtra("useremail", useremail);
            gec3.putExtra("username", username);
            gec3.putExtra("usersid", userid);
            startActivity(gec3);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        list_dashboardAraba = (ListView) findViewById(R.id.dashboardaraba_listview);
        list_dashboardEv = (ListView) findViewById(R.id.dashboardev_listview);
        Evilanlar.clear();

        for (DataSnapshot gelenler : dataSnapshot.child("ilanlar").child("ev").getChildren()) {
            Evilanlar.add(new katagori("Ev",
                    gelenler.getValue(House.class).getSehir()
                            + " , " + gelenler.getValue(House.class).getIlanTipi()
                            + "->" + gelenler.getValue(House.class).getOdaSayisi(), gelenler.getValue(House.class).getDate(), "", "", gelenler.getKey(), ""));

        }
        Collections.reverse(Evilanlar);
        final adapter Evilanadapter = new adapter(this, Evilanlar.subList(0, 3));
        list_dashboardEv.setAdapter(Evilanadapter);
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
}
