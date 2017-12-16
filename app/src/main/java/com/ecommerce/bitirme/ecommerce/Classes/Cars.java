package com.ecommerce.bitirme.ecommerce.Classes;

/**
 * Created by Sedat Er on 7.12.2017.
 */

public class Cars {
    private String modelMax;
    private String modelMin;
    private String fiyatMax;
    private String fiyatMin;
    private String baslik;
    private String aciklama;
    private String marka;
    private String sehir;
    private String yakit;
    private String vites;
    private String kasaTipi;
    private String cekis;
    private String motorHacmi;
    private String userId;
    private String date;

    public Cars() {

    }

    public Cars(String modelMax, String modelMin, String fiyatMax, String fiyatMin, String baslik, String aciklama, String marka, String sehir, String yakit, String vites, String kasaTipi, String cekis, String motorHacmi, String userId, String date) {
        this.modelMax = modelMax;
        this.modelMin = modelMin;
        this.fiyatMax = fiyatMax;
        this.fiyatMin = fiyatMin;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.marka = marka;
        this.sehir = sehir;
        this.yakit = yakit;
        this.vites = vites;
        this.kasaTipi = kasaTipi;
        this.cekis = cekis;
        this.motorHacmi = motorHacmi;
        this.userId = userId;
        this.date = date;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModelMax() {
        return modelMax;
    }

    public void setModelMax(String modelMax) {
        this.modelMax = modelMax;
    }

    public String getModelMin() {
        return modelMin;
    }

    public void setModelMin(String modelMin) {
        this.modelMin = modelMin;
    }

    public String getFiyatMax() {
        return fiyatMax;
    }

    public void setFiyatMax(String fiyatMax) {
        this.fiyatMax = fiyatMax;
    }

    public String getFiyatMin() {
        return fiyatMin;
    }

    public void setFiyatMin(String fiyatMin) {
        this.fiyatMin = fiyatMin;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getYakit() {
        return yakit;
    }

    public void setYakit(String yakit) {
        this.yakit = yakit;
    }

    public String getVites() {
        return vites;
    }

    public void setVites(String vites) {
        this.vites = vites;
    }

    public String getKasaTipi() {
        return kasaTipi;
    }

    public void setKasaTipi(String kasaTipi) {
        this.kasaTipi = kasaTipi;
    }

    public String getCekis() {
        return cekis;
    }

    public void setCekis(String cekis) {
        this.cekis = cekis;
    }

    public String getMotorHacmi() {
        return motorHacmi;
    }

    public void setMotorHacmi(String motorHacmi) {
        this.motorHacmi = motorHacmi;
    }
}
