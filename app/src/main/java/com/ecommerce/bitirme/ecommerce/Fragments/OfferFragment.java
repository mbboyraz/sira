package com.ecommerce.bitirme.ecommerce.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.Activity.AdvertActivity;
import com.ecommerce.bitirme.ecommerce.Activity.AdvertCarActivity;
import com.ecommerce.bitirme.ecommerce.Activity.adapter;
import com.ecommerce.bitirme.ecommerce.Activity.katagori;
import com.ecommerce.bitirme.ecommerce.Classes.OfferCar;
import com.ecommerce.bitirme.ecommerce.Classes.OfferHouse;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OfferFragment extends Fragment implements ValueEventListener {
    ListView listoffer;
    adapter offeradap;
    String ilanIdOffer;
    String s;
    List<katagori> offer = new ArrayList<>();
    Firebase mRef;
    TextView txtTitle;
    AlertDialog.Builder dialog;

    AdvertActivity advtactivity;
    AdvertCarActivity advtcaractivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_offer, container, false);
        txtTitle = mview.findViewById(R.id.txtTitle);
        listoffer = mview.findViewById(R.id.offerlist);
        txtTitle.setText("Teklifler");

        ilanIdOffer = getArguments().getString("ilanid");
        if (getArguments().getString("gelenact").matches("House")) {
            advtactivity = (AdvertActivity) getActivity();
            s = "house";
        } else if (getArguments().getString("gelenact").matches("Car")) {
            advtcaractivity = (AdvertCarActivity) getActivity();
            s = "car";
        }
        //// TODO: 5.01.2018 sorgu firebaseden offer doldurulacak
        Firebase.setAndroidContext(mview.getContext());

        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);


        return mview;
    }

    @Override
    public void onDataChange(final DataSnapshot dataSnapshot) {
        String offersPhotoUrl;
        offer.clear();
        for (DataSnapshot gelenler : dataSnapshot.child("teklifler").child(ilanIdOffer).getChildren()) {
            try {
                if (s.matches("house")) {
                    offersPhotoUrl = dataSnapshot.child("users")
                            .child(gelenler.getValue(OfferHouse.class).getOfferUserId()).child("usersPhotourl").getValue().toString();
                    offer.add(new katagori("Teklif",
                            gelenler.getValue(OfferHouse.class).getOfferFiyat()
                                    + " ,  " + gelenler.getValue(OfferHouse.class).getOfferm2()
                            , gelenler.getValue(OfferHouse.class).getOfferDate(), offersPhotoUrl, "", gelenler.getKey(), ""));
                } else if (s.matches("car")) {
                    offersPhotoUrl = dataSnapshot.child("users")
                            .child(gelenler.getValue(OfferCar.class).getOfferUserId()).child("usersPhotourl").getValue().toString();
                    offer.add(new katagori("Teklif",
                            gelenler.getValue(OfferCar.class).getOfferFiyat()
                                    + " ,  " + gelenler.getValue(OfferCar.class).getOfferModel() + " , " + gelenler.getValue(OfferCar.class).getOfferSehir()
                            , gelenler.getValue(OfferCar.class).getOfferDate(), offersPhotoUrl, "", gelenler.getKey(), ""));
                }
            } catch (Exception e) {
                Log.e("", e.toString());
                continue;
            }

        }
        Collections.reverse(offer);
        offeradap = new adapter(this.getActivity(), offer);
        listoffer.setAdapter(offeradap);
        listoffer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                DataSnapshot data = dataSnapshot.child("teklifler").child(ilanIdOffer).child(offer.get(position).getId());
                if (s.matches("house")) {
                    DataSnapshot data1 = dataSnapshot.child("users").child(data.getValue(OfferHouse.class).getOfferUserId());

                    advtactivity.tekliflerDialog(data.getValue(OfferHouse.class).getOfferFiyat(), data.getValue(OfferHouse.class).getOfferm2(),
                            data.getValue(OfferHouse.class).getOfferAciklama(), data.getValue(OfferHouse.class).getOfferUserId(),
                            data.getValue(OfferHouse.class).getOfferDate(), ilanIdOffer, offer.get(position).getId(), data1.child("usersName").getValue().toString(),
                            data1.child("usersPhotourl").getValue().toString(), data1.child("usersTel").getValue().toString());

                } else if (s.matches("car")) {

                    DataSnapshot data1 = dataSnapshot.child("users").child(data.getValue(OfferCar.class).getOfferUserId());
                    advtcaractivity.tekliflerDialog(data.getValue(OfferCar.class).getOfferFiyat(), data.getValue(OfferCar.class).getOfferModel(),
                            data.getValue(OfferCar.class).getOfferAciklama(), data.getValue(OfferCar.class).getOfferUserId(),
                            data.getValue(OfferCar.class).getOfferDate(), data.getValue(OfferCar.class).getOfferSehir(), ilanIdOffer, offer.get(position).getId(),
                            data1.child("usersName").getValue().toString(),
                            data1.child("usersPhotourl").getValue().toString(), data1.child("usersTel").getValue().toString());

                }
            }
        });
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }


}
