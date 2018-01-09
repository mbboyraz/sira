package com.ecommerce.bitirme.ecommerce.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.bitirme.ecommerce.Classes.Cars;
import com.ecommerce.bitirme.ecommerce.Classes.House;
import com.ecommerce.bitirme.ecommerce.Classes.OfferCar;
import com.ecommerce.bitirme.ecommerce.Classes.OfferHouse;
import com.ecommerce.bitirme.ecommerce.Classes.OfferTelephone;
import com.ecommerce.bitirme.ecommerce.Classes.Telephone;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyProfile extends AppCompatActivity {


    static String ilanIdOffer;
    ImageView profilephotos;
    String userid, name;
    Firebase mRef;
    String s;
    ArrayList<String> ilanno = new ArrayList<>();
    EditText offer_m2_edt;
    AlertDialog.Builder dialog;
    TextView offer_m2_txt;
    ImageButton imgbtn_offers, imgbtn_offersAra;
    LinearLayout sehirlay;

    DateFormat current;
    Date currentTime;
    String currentDate;

    EditText offer_fiyat_edt, offer_model_edt, offer_tarih_edt, offer_aciklama_edt, offer_sehir_edt;
    TextView offer_fiyat_txt, offer_model1_txt, offer_model2_txt, offer_tarih_txt, offer_aciklama_txt, offersAd, tel_txt, offer_sehir1_txt, offer_sehir2_txt;

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

    public String getData() {
        return userid;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Firebase.setAndroidContext(this);


        currentTime = new Date();
        current = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = current.format(currentTime);

        profilephotos = (ImageView) findViewById(R.id.profilphoto);
        Bundle extras = getIntent().getExtras();
        userid = extras.getString("usersid");

        Picasso.with(MyProfile.this).load(extras.getString("profilephoto")).into(profilephotos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new PlaceholderFragment.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




    public AlertDialog.Builder tekliflerEvDialog(String fiyat, String m2, String aciklama, String user, String date, String ilanid, String teklifid, String offersname, String offersphoto, final String offerstel) {


        dialog = new AlertDialog.Builder(MyProfile.this);
        View nview = getLayoutInflater().inflate(R.layout.show_offer_dialog, null);
        offer_fiyat_txt = nview.findViewById(R.id.fiyatbilgi_txt);
        offer_m2_txt = nview.findViewById(R.id.m2bilgi_txt);
        offer_tarih_txt = nview.findViewById(R.id.tekliftarihi_txt);
        offer_aciklama_txt = nview.findViewById(R.id.aciklama_txt);
        offer_fiyat_edt = nview.findViewById(R.id.fiyataraligi_edt);
        offer_m2_edt = nview.findViewById(R.id.m2aralig_edt);
        offer_tarih_edt = nview.findViewById(R.id.tekliftarihi_edt);
        offer_aciklama_edt = nview.findViewById(R.id.aciklama_edt);
        offersAd = nview.findViewById(R.id.teklifverenad_txt);
        imgbtn_offers = nview.findViewById(R.id.teklifveren_imgbtn);
        imgbtn_offersAra = nview.findViewById(R.id.offersArama_imgbtn);
        offersAd.setText(offersname);
        sehirlay = nview.findViewById(R.id.sehir_lay);
        sehirlay.setVisibility(View.GONE);
        Picasso.with(nview.getContext()).load(offersphoto).into(imgbtn_offers);
        offer_fiyat_txt.setVisibility(View.GONE);
        offer_m2_txt.setVisibility(View.GONE);
        offer_tarih_txt.setVisibility(View.GONE);
        offer_aciklama_txt.setVisibility(View.GONE);


        imgbtn_offersAra.setVisibility(View.GONE);

        offer_fiyat_edt.setText(fiyat);
        offer_m2_edt.setText(m2);
        offer_tarih_edt.setText(currentDate);
        offer_tarih_edt.setEnabled(false);
        offer_aciklama_edt.setText(aciklama);
        dialog.setPositiveButton("Düzenle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //// TODO: 7.01.2018 Verilen teklif bilgileri burada edt text de hazır getirilip düzenlenecek

            }
        });
        dialog.setNegativeButton("Sil", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //// TODO: 7.01.2018 teklif firebaseden silinecek
            }
        });
        dialog.setNeutralButton("Kapat", null);


        dialog.setView(nview);
        AlertDialog mdialog = dialog.create();
        mdialog.show();
        return dialog;
    }

    public AlertDialog.Builder tekliflerArabaDialog(String fiyat, String model, String aciklama, String user, String date, String sehir, String ilanid, String teklifid, String offersname, String offersphoto, final String offerstel) {


        dialog = new AlertDialog.Builder(MyProfile.this);
        View nview = getLayoutInflater().inflate(R.layout.show_offer_dialog, null);
        offer_fiyat_txt = nview.findViewById(R.id.fiyatbilgi_txt);
        offer_model1_txt = nview.findViewById(R.id.m2_txt);
        offer_model2_txt = nview.findViewById(R.id.m2bilgi_txt);
        offer_tarih_txt = nview.findViewById(R.id.tekliftarihi_txt);
        offer_aciklama_txt = nview.findViewById(R.id.aciklama_txt);
        offer_sehir1_txt = nview.findViewById(R.id.txt_sehircar1);
        offer_sehir2_txt = nview.findViewById(R.id.txt_sehircar2);

        offer_fiyat_edt = nview.findViewById(R.id.fiyataraligi_edt);
        offer_model_edt = nview.findViewById(R.id.m2aralig_edt);
        offer_tarih_edt = nview.findViewById(R.id.tekliftarihi_edt);
        offer_sehir_edt = nview.findViewById(R.id.edt_sehircar);
        offer_aciklama_edt = nview.findViewById(R.id.aciklama_edt);
        offersAd = nview.findViewById(R.id.teklifverenad_txt);
        imgbtn_offers = nview.findViewById(R.id.teklifveren_imgbtn);
        imgbtn_offersAra = nview.findViewById(R.id.offersArama_imgbtn);
        offersAd.setText(offersname);
        Picasso.with(nview.getContext()).load(offersphoto).into(imgbtn_offers);


        offer_fiyat_edt.setText(fiyat);
        offer_model_edt.setText(model);
        offer_model1_txt.setText("Model");
        offer_tarih_edt.setText(currentDate);
        offer_sehir_edt.setText(sehir);
        offer_aciklama_edt.setText(aciklama);

        offer_fiyat_txt.setVisibility(View.GONE);
        offer_model2_txt.setVisibility(View.GONE);
        offer_tarih_txt.setVisibility(View.GONE);
        offer_sehir2_txt.setVisibility(View.GONE);
        offer_aciklama_txt.setVisibility(View.GONE);

        offer_tarih_edt.setEnabled(false);

        imgbtn_offersAra.setVisibility(View.GONE);
        dialog.setPositiveButton("Düzenle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //// TODO: 7.01.2018 Verilen teklif bilgileri burada edt text de hazır getirilip düzenlenecek

            }
        });
        dialog.setNegativeButton("Sil", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //// TODO: 7.01.2018 teklif firebaseden silinecek
            }
        });
        dialog.setNeutralButton("Kapat", null);


        dialog.setView(nview);
        AlertDialog mdialog = dialog.create();
        mdialog.show();
        return dialog;
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
        List<katagori> teklifler = new ArrayList<katagori>();
        adapter ilanadapter;
        ListView ilanlarliste;
        int i;
        String userid;
        AlertDialog.Builder dialog;

        Map<String, ArrayList<String>> ids = new HashMap<>();
        ArrayList<String> idler = new ArrayList<>();
        ArrayList<String> donilan = new ArrayList<>();

        MyProfile activity;


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
            //  TextView textView = rootView.findViewById(R.id.section_label);
            // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            // my.userid

            activity = (MyProfile) getActivity();
            userid = activity.getData();

            i = getArguments().getInt(ARG_SECTION_NUMBER);
            Firebase.setAndroidContext(this.getActivity());
            mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
            mRef.addValueEventListener(this);
            Toast.makeText(getContext(), userid, Toast.LENGTH_SHORT).show();
            return rootView;
        }


        @Override
        public void onDataChange(final DataSnapshot dataSnapshot) {
            int k = 0, l = 0;
            DataSnapshot data;
            String offersPhotoUrl;

            if (i == 1) {
                ilanlar.clear();
                for (DataSnapshot evid : dataSnapshot.child("users").child(userid).child("ilanlarım").child("ev").getChildren()) {

                    data = dataSnapshot.child("ilanlar").child("ev").child(evid.getValue().toString());

                    ilanlar.add(new katagori("Ev",
                            data.getValue(House.class).getSehir() + " , " +
                                    data.getValue(House.class).getIlanTipi() + "->" +
                                    data.getValue(House.class).getOdaSayisi(), data.getValue(House.class).getDate(), "", "", data.getKey(), ""));
                }
                for (DataSnapshot arabaid : dataSnapshot.child("users").child(userid).child("ilanlarım").child("araba").getChildren()) {

                    data = dataSnapshot.child("ilanlar").child("araba").child(arabaid.getValue().toString());

                    ilanlar.add(new katagori("Araba",
                            data.getValue(Cars.class).getMarka() + " , " +
                                    data.getValue(Cars.class).getModelMin() + "-" +
                                    data.getValue(Cars.class).getModelMax() + " , " +
                                    data.getValue(Cars.class).getFiyatMin() + "-" +
                                    data.getValue(Cars.class).getFiyatMax(), data.getValue(Cars.class).getDate(), "", "", data.getKey(), ""));
                }
                for (DataSnapshot telefonid : dataSnapshot.child("users").child(userid).child("ilanlarım").child("telefon").getChildren()) {

                    data = dataSnapshot.child("ilanlar").child("telefon").child(telefonid.getValue().toString());

                    ilanlar.add(new katagori("Telefon",
                            data.getValue(Telephone.class).getTelMarka()
                                    + " , " + data.getValue(Telephone.class).getTelModel()
                                    + "-" + data.getValue(Telephone.class).getTelPriceMin()
                                    + " , " + data.getValue(Telephone.class).getTelPriceMax(),
                            data.getValue(Telephone.class).getTelDate(), "", "", data.getKey(), ""));
                }


                Collections.reverse(ilanlar);
                adapter ilanadapter = new adapter(this.getActivity(), ilanlar);
                ilanlarliste.setAdapter(ilanadapter);
                ilanlarliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (ilanlar.get(position).getKatagoriName().matches("Ev")) {
                            Intent intent = new Intent(getContext(), AdvertActivity.class);
                            intent.putExtra("id", ilanlar.get(position).getId());
                            intent.putExtra("userid", userid);
                            startActivity(intent);
                        } else if (ilanlar.get(position).getKatagoriName().matches("Araba")) {
                            Intent intent = new Intent(getContext(), AdvertCarActivity.class);
                            intent.putExtra("id", ilanlar.get(position).getId());
                            intent.putExtra("userid", userid);
                            startActivity(intent);
                        } else if (ilanlar.get(position).getKatagoriName().matches("Telefon")) {
                            Intent intent = new Intent(getContext(), AdvertTelephoneAcivity.class);
                            intent.putExtra("id", ilanlar.get(position).getId());
                            intent.putExtra("userid", userid);
                            startActivity(intent);
                        }

                        // Toast.makeText(view.getContext(), "Uçuyozz", Toast.LENGTH_LONG).show();
                    }
                });
            } else if (i == 2) {

                teklifler.clear();
                for (DataSnapshot evid : dataSnapshot.child("users").child(userid).child("tekliflerim").child("ev").getChildren()) {

                    try {
                        data = dataSnapshot.child("teklifler").child(evid.getKey()).child(evid.getValue().toString());

                        offersPhotoUrl = dataSnapshot.child("users").child(data.getValue(OfferHouse.class).getOfferUserId()).child("usersPhotourl").getValue().toString();

                        teklifler.add(new katagori("Teklif", "Fiyat -> " +
                                data.getValue(OfferHouse.class).getOfferFiyat()
                                + " , m² -> " + data.getValue(OfferHouse.class).getOfferm2()
                                , data.getValue(OfferHouse.class).getOfferDate(), offersPhotoUrl, evid.getKey(), evid.getValue().toString(), "ev"));

                    } catch (Exception e) {
                        mRef.child("users").child(userid).child("tekliflerim").child("ev").child(evid.getKey()).removeValue();
                    }
                }
                for (DataSnapshot arabaid : dataSnapshot.child("users").child(userid).child("tekliflerim").child("araba").getChildren()) {

                    try {
                        data = dataSnapshot.child("teklifler").child(arabaid.getKey()).child(arabaid.getValue().toString());

                        offersPhotoUrl = dataSnapshot.child("users").child(data.getValue(OfferCar.class).getOfferUserId()).child("usersPhotourl").getValue().toString();
                        teklifler.add(new katagori("Teklif", "Fiyat -> " +
                                data.getValue(OfferCar.class).getOfferFiyat()
                                + " , Model -> " + data.getValue(OfferCar.class).getOfferModel() + " , " + data.getValue(OfferCar.class).getOfferSehir()
                                , data.getValue(OfferCar.class).getOfferDate(), offersPhotoUrl, arabaid.getKey(), arabaid.getValue().toString(), "araba"));

                    } catch (Exception e) {
                        mRef.child("users").child(userid).child("tekliflerim").child("araba").child(arabaid.getKey()).removeValue();
                    }
                }
                for (DataSnapshot telefonid : dataSnapshot.child("users").child(userid).child("tekliflerim").child("telefon").getChildren()) {

                    try {
                        data = dataSnapshot.child("teklifler").child(telefonid.getKey()).child(telefonid.getValue().toString());

                        offersPhotoUrl = dataSnapshot.child("users").child(data.getValue(OfferTelephone.class).getOfferUserId()).child("usersPhotourl").getValue().toString();
                        teklifler.add(new katagori("Teklif", "Fiyat -> " +
                                data.getValue(OfferTelephone.class).getOfferFiyat()
                                + " , Model -> " + data.getValue(OfferTelephone.class).getOfferModel() + " , " + data.getValue(OfferTelephone.class).getOfferSehir()
                                , data.getValue(OfferTelephone.class).getOfferDate(), offersPhotoUrl, telefonid.getKey(), telefonid.getValue().toString(), "telefon"));

                    } catch (Exception e) {
                        mRef.child("users").child(userid).child("tekliflerim").child("telefon").child(telefonid.getKey()).removeValue();
                    }
                }
                Collections.reverse(teklifler);
                adapter ilanadapter = new adapter(this.getActivity(), teklifler);
                ilanlarliste.setAdapter(ilanadapter);
                ilanlarliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DataSnapshot dataSnapshot1 = dataSnapshot.child("teklifler").child(teklifler.get(position).getKatagoriIlanid()).child(teklifler.get(position).getId());
                        DataSnapshot data1 = dataSnapshot.child("users").child(userid);
                        if (teklifler.get(position).getTeklifTur().matches("ev")) {
                            activity.tekliflerEvDialog(
                                    dataSnapshot1.getValue(OfferHouse.class).getOfferFiyat(),
                                    dataSnapshot1.getValue(OfferHouse.class).getOfferm2(),
                                    dataSnapshot1.getValue(OfferHouse.class).getOfferAciklama(),
                                    dataSnapshot1.getValue(OfferHouse.class).getOfferUserId(),
                                    dataSnapshot1.getValue(OfferHouse.class).getOfferDate(),
                                    teklifler.get(position).getKatagoriIlanid(),
                                    teklifler.get(position).getId(),
                                    data1.child("usersName").getValue().toString(),
                                    data1.child("usersPhotourl").getValue().toString(),
                                    data1.child("usersTel").getValue().toString());
                        } else if (teklifler.get(position).getTeklifTur().matches("araba")) {
                            activity.tekliflerArabaDialog(
                                    dataSnapshot1.getValue(OfferCar.class).getOfferFiyat(),
                                    dataSnapshot1.getValue(OfferCar.class).getOfferModel(),
                                    dataSnapshot1.getValue(OfferCar.class).getOfferAciklama(),
                                    dataSnapshot1.getValue(OfferCar.class).getOfferUserId(),
                                    dataSnapshot1.getValue(OfferCar.class).getOfferDate(),
                                    dataSnapshot1.getValue(OfferCar.class).getOfferSehir(),
                                    teklifler.get(position).getKatagoriIlanid(),
                                    teklifler.get(position).getId(),
                                    data1.child("usersName").getValue().toString(),
                                    data1.child("usersPhotourl").getValue().toString(),
                                    data1.child("usersTel").getValue().toString());

                        } else if (teklifler.get(position).getTeklifTur().matches("telefon")) {
                            activity.tekliflerArabaDialog(dataSnapshot1.getValue(OfferTelephone.class).getOfferFiyat(),
                                    dataSnapshot1.getValue(OfferTelephone.class).getOfferModel(),
                                    dataSnapshot1.getValue(OfferTelephone.class).getOfferAciklama(),
                                    dataSnapshot1.getValue(OfferTelephone.class).getOfferUserId(),
                                    dataSnapshot1.getValue(OfferTelephone.class).getOfferDate(),
                                    dataSnapshot1.getValue(OfferTelephone.class).getOfferSehir(),
                                    teklifler.get(position).getKatagoriIlanid(),
                                    teklifler.get(position).getId(),
                                    data1.child("usersName").getValue().toString(),
                                    data1.child("usersPhotourl").getValue().toString(),
                                    data1.child("usersTel").getValue().toString());
                        }

                    }
                });


            }


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
                        return "TEKLİFLERİM";
              /*  case 2:
                    return "SECTION 3";*/
                }
                return null;
            }
        }
    }
}
