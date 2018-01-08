package com.ecommerce.bitirme.ecommerce.Activity;

/**
 * Created by Sedat Er on 24.11.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapter extends BaseAdapter {

    ImageView simge;
    private List<katagori> userList;
    private Activity activity;

    public adapter(Activity activity, List<katagori> userList) {
        this.activity = activity;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View lineView = inflater.inflate(R.layout.satir_layout, viewGroup, false);

        TextView kategori = lineView.findViewById(R.id.kategori);
        TextView altkategori = lineView.findViewById(R.id.altkategori);
        TextView date = lineView.findViewById(R.id.katagori_date_txt);
        simge = lineView.findViewById(R.id.simge);

        katagori user = userList.get(i);
        kategori.setText(user.getKatagoriName());
        altkategori.setText(user.getKatagoriAltName());
        date.setText(user.getKatagoriDate());
        if (user.getKatagoriName().equals("Ev")) {
            simge.setImageResource(R.drawable.ic_home_black_24dp);
        } else if (user.getKatagoriName().equals("Araba")) {
            simge.setImageResource(R.drawable.ic_drive_eta_black_24dp);
        } else if (user.getKatagoriName().equals("Telefon")) {
            simge.setImageResource(R.drawable.ic_phone_iphone_black_24dp);
        } else if (user.getKatagoriName().equals("Teklif")) {
            Picasso.with(this.activity).load(user.getKatagoriPhoto()).into(simge);
        }


        //  simge.setImageResource(R.drawable.ic_menu_send);


        return lineView;
    }
}