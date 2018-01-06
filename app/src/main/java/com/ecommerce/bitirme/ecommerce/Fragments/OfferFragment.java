package com.ecommerce.bitirme.ecommerce.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.Activity.adapter;
import com.ecommerce.bitirme.ecommerce.Activity.katagori;
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
    List<katagori> offer = new ArrayList<>();
    Firebase mRef;
    TextView txtTitle;
    AlertDialog.Builder dialog;

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
        //// TODO: 5.01.2018 sorgu firebaseden offer doldurulacak
        Firebase.setAndroidContext(mview.getContext());

        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");
        mRef.addValueEventListener(this);


        return mview;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String offersPhotoUrl;
        offer.clear();
        for (DataSnapshot gelenler : dataSnapshot.child("teklifler").child(ilanIdOffer).getChildren()) {
            try {
                offersPhotoUrl = dataSnapshot.child("users")
                        .child(gelenler.getValue(OfferHouse.class).getOfferUserId()).child("usersPhotourl").getValue().toString();
                offer.add(new katagori("Teklif",
                        gelenler.getValue(OfferHouse.class).getOfferFiyat() + " , " + gelenler.getValue(OfferHouse.class).getOfferm2() + " , " + gelenler.getValue(OfferHouse.class).getOfferDate() + "----" + gelenler.getKey(), offersPhotoUrl));
            } catch (Exception e) {

                continue;
            }

        }
        Collections.reverse(offer);
        offeradap = new adapter(this.getActivity(), offer);
        listoffer.setAdapter(offeradap);
        listoffer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dialog = new AlertDialog.Builder(view.getContext());
            }
        });
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }


}
