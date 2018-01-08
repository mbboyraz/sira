package com.ecommerce.bitirme.ecommerce.Fragments.DashboardFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ecommerce.bitirme.ecommerce.Activity.adapter;
import com.ecommerce.bitirme.ecommerce.Activity.katagori;
import com.ecommerce.bitirme.ecommerce.Classes.House;
import com.ecommerce.bitirme.ecommerce.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyAdvertsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyAdvertsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAdvertsFragment extends Fragment implements ValueEventListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    List<katagori> ilanlar = new ArrayList<katagori>();
    Firebase mRef;

    adapter ilanadapter;
    ListView liste;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MyAdvertsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyAdvertsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyAdvertsFragment newInstance(String param1, String param2) {
        MyAdvertsFragment fragment = new MyAdvertsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Firebase.setAndroidContext(this.getActivity());
        mRef = new Firebase("https://ecommerce-1-28620.firebaseio.com/");

        View mView = inflater.inflate(R.layout.fragment_my_profile, container, false);
        liste = mView.findViewById(R.id.liste);
        mRef.addValueEventListener(this);

        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {


        for (DataSnapshot gelenler : dataSnapshot.child("ilanlar").child("ev").getChildren()) {
            ilanlar.add(new katagori("Ev",
                    gelenler.getValue(House.class).getSehir()
                            + " , " + gelenler.getValue(House.class).getIlanTipi()
                            + "->" + gelenler.getValue(House.class).getOdaSayisi(),
                    "İlan Tarihi : " + gelenler.getValue(House.class).getDate(), "", "", gelenler.getKey(), ""));
        }


              /*  for (DataSnapshot gelenler1 : dataSnapshot.child("ilanlar").child("araba").getChildren()) {
                    ilanlar.add(new katagori("Araba",
                            gelenler1.getValue(Cars.class).getMarka() + " , " + gelenler1.getValue(Cars.class).getModelMin() + "-" + gelenler1.getValue(Cars.class).getModelMax() + " , " + gelenler1.getValue(Cars.class).getFiyatMin() + "-" + gelenler1.getValue(Cars.class).getFiyatMax(), gelenler1.getKey()));
                }*/

        //house.ilanAciklama = dataSnapshot.child("ilanlar").child("ev").child("3").child("ilanAciklama").getValue().toString();
        //     Toast.makeText(this, dataSnapshot.child("ilanlar").child("ev").child("14").child("ilanAciklama").getValue().toString(), Toast.LENGTH_SHORT).show();
//        for(DataSnapshot gelenler:dataSnapshot.child("ilanlar").child("ev").getChildren()){
//            Toast.makeText(this,gelenler.getValue(House.class).ilanAciklama+"\n",Toast.LENGTH_SHORT);
//        }


        ilanadapter = new adapter(getActivity(), ilanlar);
        liste.setAdapter(ilanadapter);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  /*  Intent intent = new Intent(AllAdvertsActivity.this, AdvertActivity.class);
                    intent.putExtra("id", ilanlar.get(position).getId());
                    startActivity(intent);*/
                // Toast.makeText(view.getContext(), "Uçuyozz", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
