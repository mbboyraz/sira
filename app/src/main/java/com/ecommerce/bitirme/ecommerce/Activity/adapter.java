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

import java.util.List;

public class adapter extends BaseAdapter{

    private LayoutInflater userInflater;
    private List<katagori> userList;

    public adapter(Activity activity,List<katagori> userList){
        userInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userList=userList;
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
        View lineView;
        lineView = userInflater.inflate(R.layout.satir_layout, null);
        TextView kategori = lineView.findViewById(R.id.kategori);
        TextView altkategori = lineView.findViewById(R.id.altkategori);
        ImageView simge = lineView.findViewById(R.id.simge);

        katagori user = userList.get(i);
        kategori.setText(user.getKatagoriName());
        altkategori.setText(user.getKatagoriAltName());
        if (user.getKatagoriName().equals("Ev")) {
            simge.setImageResource(R.drawable.ic_home_black_24dp);
        }
        else if(user.getKatagoriName().equals("Araba")){
            simge.setImageResource(R.drawable.ic_drive_eta_black_24dp);
        }
        else if(user.getKatagoriName().equals("Spor")){
            simge.setImageResource(R.drawable.ic_fitness_center_black_24dp);
        }
        else if(user.getKatagoriName().equals("Teknoloji")){
            simge.setImageResource(R.drawable.ic_phone_iphone_black_24dp);
        }
        else if(user.getKatagoriName().equals("Taşıtlar")){
            simge.setImageResource(R.drawable.ic_motorcycle_black_24dp);
        }


          //  simge.setImageResource(R.drawable.ic_menu_send);




        return lineView;
    }
}