package com.ecommerce.bitirme.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.Classes.Cars;
import com.ecommerce.bitirme.ecommerce.Classes.House;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyProfile extends AppCompatActivity {


    ImageView profilephotos;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private PlaceholderFragment.SectionsPagerAdapter mSectionsPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);



        profilephotos = (ImageView) findViewById(R.id.profilphoto);
        Bundle extras = getIntent().getExtras();
        Picasso.with(MyProfile.this).load(extras.getString("profilephoto")).into(profilephotos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new PlaceholderFragment.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prof= new Intent(MyProfile.this,katagoriactivity.class);
                startActivity(prof);
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_profile, menu);
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


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements ValueEventListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        Firebase mRef;
        List<katagori> ilanlar = new ArrayList<katagori>();
        adapter ilanadapter;
        ListView ilanlarliste;
        int i;



        public PlaceholderFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);
            ilanlarliste = rootView.findViewById(R.id.liste);
            TextView textView = rootView.findViewById(R.id.section_label);
            // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            i = getArguments().getInt(ARG_SECTION_NUMBER);
            Firebase.setAndroidContext(this.getActivity());
            mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
            mRef.addValueEventListener(this);

            return rootView;
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (i == 1) {
            for (DataSnapshot gelenler : dataSnapshot.child("ilanlar").child("ev").getChildren()) {
                ilanlar.add(new katagori("Ev",
                        gelenler.getValue(House.class).getSehir() + " , " + gelenler.getValue(House.class).getIlanTipi() + "->" + gelenler.getValue(House.class).getOdaSayisi(), gelenler.getKey()));
            }
            } else if (i == 2) {
                for (DataSnapshot gelenler1 : dataSnapshot.child("ilanlar").child("araba").getChildren()) {
                    ilanlar.add(new katagori("Araba",
                            gelenler1.getValue(Cars.class).getMarka() + " , " + gelenler1.getValue(Cars.class).getModelMin() + "-" + gelenler1.getValue(Cars.class).getModelMax() + " , " + gelenler1.getValue(Cars.class).getFiyatMin() + "-" + gelenler1.getValue(Cars.class).getFiyatMax(), gelenler1.getKey()));
                }
            }
            ilanadapter = new adapter(this.getActivity(), ilanlar);
            ilanlarliste.setAdapter(ilanadapter);
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }


        /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
        public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "İLANLARIM";
                case 1:
                    return "TEKLİFLER";
              /*  case 2:
                    return "SECTION 3";*/
            }
            return null;
        }
    }
}
}
