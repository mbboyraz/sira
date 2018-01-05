package com.ecommerce.bitirme.ecommerce.Classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sedat Er on 6.12.2017.
 */

public class House {
    public String ilanTipi;
    public String odaSayisi;
    public String minM2;
    public String maxM2;
    public boolean krediyeUygun;
    public String ilanAciklama;
    public String sehir;
    public String semt;
    public String maxFiyat;
    public String minFiyat;
    public String userid;
    public String date;

    public House(String ilanTipi, String odaSayisi, String minM2, String maxM2, boolean krediyeUygun, String ilanAciklama, String sehir, String maxFiyat, String minFiyat, String userid, String date) {
        this.ilanTipi = ilanTipi;
        this.odaSayisi = odaSayisi;
        this.minM2 = minM2;
        this.maxM2 = maxM2;
        this.krediyeUygun = krediyeUygun;
        this.ilanAciklama = ilanAciklama;
        this.sehir = sehir;
        this.minFiyat = minFiyat;
        this.maxFiyat = maxFiyat;
        this.userid = userid;
        this.date = date;

        //   mapDondur();
    }

    public House() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Map<String, String> mapDondur() {


        Map<String, String> ilan = new HashMap<String, String>();
        ilan.put("ilantipi", ilanTipi);
        ilan.put("odasayisi", odaSayisi);
        ilan.put("minm2", minM2);
        ilan.put("maxm2", maxM2);
        ilan.put("krediyeuygun", String.valueOf(krediyeUygun));
        ilan.put("ilanaciklama", ilanAciklama);
        ilan.put("sehir", sehir);
        ilan.put("maxfiyat", maxFiyat);
        ilan.put("minfiyat", minFiyat);
        return ilan;

    }

    public String getIlanTipi() {
        return ilanTipi;
    }

    public void setIlanTipi(String ilanTipi) {
        this.ilanTipi = ilanTipi;
    }

    public String getOdaSayisi() {
        return odaSayisi;
    }

    public void setOdaSayisi(String odaSayisi) {
        this.odaSayisi = odaSayisi;
    }

    public String getMinM2() {
        return minM2;
    }

    public void setMinM2(String minM2) {
        this.minM2 = minM2;
    }

    public String getMaxM2() {
        return maxM2;
    }

    public void setMaxM2(String maxM2) {
        this.maxM2 = maxM2;
    }

    public boolean isKrediyeUygun() {
        return krediyeUygun;
    }

    public void setKrediyeUygun(boolean krediyeUygun) {
        this.krediyeUygun = krediyeUygun;
    }

    public String getIlanAciklama() {
        return ilanAciklama;
    }

    public void setIlanAciklama(String ilanAciklama) {
        this.ilanAciklama = ilanAciklama;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getSemt() {
        return semt;
    }

    public void setSemt(String semt) {
        this.semt = semt;
    }

    public String getMaxFiyat() {
        return maxFiyat;
    }

    public void setMaxFiyat(String maxFiyat) {
        this.maxFiyat = maxFiyat;
    }

    public String getMinFiyat() {
        return minFiyat;
    }

    public void setMinFiyat(String minFiyat) {
        this.minFiyat = minFiyat;
    }
}
