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

import com.ecommerce.bitirme.ecommerce.R;

public class DasboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
       // navigationView.getMenu().getItem(1).setTitle("burak");
        navigationView.setNavigationItemSelectedListener(this);
//------------------------------
     /*   TextView textView1 = (TextView) findViewById(R.id.tv_first);
        TextView textView2 = (TextView) findViewById(R.id.tv_second);
    /*  TextView textView3 = (TextView) findViewById(R.id.tv_third);
        TextView textView4 = (TextView) findViewById(R.id.tv_four);
        TextView textView5 = (TextView) findViewById(R.id.tv_five);*/


     /*   ImageView imageView = (ImageView) findViewById(R.id.imageView_cursor);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        TextView[] textViews = {textView1, textView2};

        View view1 = View.inflate(this, R.layout.view_1, null);
        View view2 = View.inflate(this, R.layout.view_2, null);
       /* View view3 = View.inflate(this, R.layout.view_3, null);
        View view4 = View.inflate(this, R.layout.view_4, null);
        View view5 = View.inflate(this, R.layout.view_5, null);*/

    /*    List<View> listViews = new ArrayList<>();
        listViews.add(view1);
        listViews.add(view2);
     /*   listViews.add(view3);
        listViews.add(view4);
        listViews.add(view5);*/

       /* ViewPagerSwitch
                .getInstance()
                .init(this)
                .addViewPager(viewPager)
                .addTabs(textViews)
                .addChildViews(listViews)
                .setSelectedColor(R.color.colorAccent1)
                .setUnSelectedColor(R.color.colorAccent2)
                .addCursor(imageView)
                .build();*/
   //     Intent launchIntent = getPackageManager().getLaunchIntentForPackage("cn.scbbc.easyviewpagerslide");
    //    startActivity(launchIntent);
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
