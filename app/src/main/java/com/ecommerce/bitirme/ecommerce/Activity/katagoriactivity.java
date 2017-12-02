package com.ecommerce.bitirme.ecommerce.Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ecommerce.bitirme.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

public class katagoriactivity extends AppCompatActivity {
    final List<katagori> categories = new ArrayList<katagori>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katagoriactivity);
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        /* mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/
        categories.add(new katagori("Ev"));
        categories.add(new katagori("Araba"));
        categories.add(new katagori("Spor"));
        categories.add(new katagori("Teknoloji"));
        categories.add(new katagori("Taşıtlar"));
      /*  categories.add(new katagori("Ustalar ve Hizmetler"));
        categories.add(new katagori("Özel Ders Verenler"));
        categories.add(new katagori("İş İlanları"));
        categories.add(new katagori("Yardımcı Arayanlar"));
        categories.add(new katagori("Hayvanlar Alemi"));*/
        final ListView liste = (ListView) findViewById(R.id.liste);
        adapter adapterr = new adapter(this, categories);
        liste.setAdapter(adapterr);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent gec= new Intent(katagoriactivity.this,AddAdvert.class);
                String s;

                  if(i==0){
                    s="Ev İlanı";
                }
                else if(i==1){
                    s="Araba İlanı";
                }
                  else if(i==2){
                      s="Spor İlanı";
                  }
                  else if(i==3){
                      s="Teknoloji İlanı";
                  }
                else s="Taşıt ilanı";
                s = categories.get(i).getKatagoriName();
                 gec.putExtra("session", s);
                 startActivity(gec);
            }
        });
    }
}
