package com.ecommerce.bitirme.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.Classes.Cars;
import com.ecommerce.bitirme.ecommerce.Classes.House;
import com.ecommerce.bitirme.ecommerce.Classes.Telephone;
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
        implements NavigationView.OnNavigationItemSelectedListener, ValueEventListener, AdapterView.OnItemClickListener {
    ImageView img_left_menu_photo;
    TextView txt_left_menu_user_name;
    TextView txt_left_menu_user_email;
    NavigationView nav_drawer;
    boolean isFirst = false;
    String photourl, username, useremail, userid;

    ListView list_dashboardEv, list_dashboardAraba, list_dashboardTelefon;

    Firebase mRef;
    List<katagori> Evilanlar = new ArrayList<katagori>();
    List<katagori> Arabailanlar = new ArrayList<katagori>();
    List<katagori> Telefonilanlar = new ArrayList<katagori>();


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
            gec1.putExtra("usersid", userid);
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
        list_dashboardTelefon = (ListView) findViewById(R.id.dashboardtelefon_listview);

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
        list_dashboardEv.setOnItemClickListener(this);

        Arabailanlar.clear();
        for (DataSnapshot gelenler1 : dataSnapshot.child("ilanlar").child("araba").getChildren()) {
            Arabailanlar.add(new katagori("Araba",
                    gelenler1.getValue(Cars.class).getMarka()
                            + " , " + gelenler1.getValue(Cars.class).getModelMin()
                            + "-" + gelenler1.getValue(Cars.class).getModelMax()
                            + " , " + gelenler1.getValue(Cars.class).getFiyatMin()
                            + "-" + gelenler1.getValue(Cars.class).getFiyatMax(), gelenler1.getValue(Cars.class).getDate(), "", "", gelenler1.getKey(), ""));


        }
        Collections.reverse(Arabailanlar);
        final adapter Arabailanadapter = new adapter(this, Arabailanlar.subList(0, 3));
        list_dashboardAraba.setAdapter(Arabailanadapter);
        list_dashboardAraba.setOnItemClickListener(this);

        Telefonilanlar.clear();
        for (DataSnapshot gelenler1 : dataSnapshot.child("ilanlar").child("telefon").getChildren()) {
            Telefonilanlar.add(new katagori("Telefon",
                    gelenler1.getValue(Telephone.class).getTelMarka()
                            + " , " + gelenler1.getValue(Telephone.class).getTelModel()
                            + "-" + gelenler1.getValue(Telephone.class).getTelPriceMin()
                            + " , " + gelenler1.getValue(Telephone.class).getTelPriceMax(),
                    gelenler1.getValue(Telephone.class).getTelDate(), "", "", gelenler1.getKey(), ""));


        }
        Collections.reverse(Telefonilanlar);
        final adapter Telefonilanadapter = new adapter(this, Telefonilanlar.subList(0, 3));
        list_dashboardTelefon.setAdapter(Telefonilanadapter);
        list_dashboardTelefon.setOnItemClickListener(this);
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.dashboardev_listview:
                Intent intent = new Intent(DasboardActivity.this, AdvertActivity.class);
                intent.putExtra("id", Evilanlar.get(position).getId());
                intent.putExtra("userid", userid);
                startActivity(intent);
                break;
            case R.id.dashboardaraba_listview:
                Intent intent1 = new Intent(DasboardActivity.this, AdvertCarActivity.class);
                intent1.putExtra("id", Arabailanlar.get(position).getId());
                intent1.putExtra("userid", userid);
                startActivity(intent1);
                break;
            case R.id.dashboardtelefon_listview:
                Intent intent2 = new Intent(DasboardActivity.this, AdvertTelephoneAcivity.class);
                intent2.putExtra("id", Telefonilanlar.get(position).getId());
                intent2.putExtra("userid", userid);
                startActivity(intent2);

        }

    }
}
