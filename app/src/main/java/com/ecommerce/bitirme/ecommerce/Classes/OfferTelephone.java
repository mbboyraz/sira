package com.ecommerce.bitirme.ecommerce.Classes;

/**
 * Created by mbura on 8.01.2018.
 */

public class OfferTelephone {
    private String offerFiyat;
    private String offerModel;
    private String offerSehir;
    private String offerAciklama;
    private String offerDate;
    private String offerUserId;

    public OfferTelephone() {

    }

    public OfferTelephone(String offerFiyat, String offerModel, String offerSehir, String offerAciklama, String offerDate, String offerUserId) {
        this.offerFiyat = offerFiyat;
        this.offerModel = offerModel;
        this.offerSehir = offerSehir;
        this.offerAciklama = offerAciklama;
        this.offerDate = offerDate;
        this.offerUserId = offerUserId;
    }

    public String getOfferFiyat() {
        return offerFiyat;
    }

    public void setOfferFiyat(String offerFiyat) {
        this.offerFiyat = offerFiyat;
    }

    public String getOfferModel() {
        return offerModel;
    }

    public void setOfferModel(String offerModel) {
        this.offerModel = offerModel;
    }

    public String getOfferSehir() {
        return offerSehir;
    }

    public void setOfferSehir(String offerSehir) {
        this.offerSehir = offerSehir;
    }

    public String getOfferAciklama() {
        return offerAciklama;
    }

    public void setOfferAciklama(String offerAciklama) {
        this.offerAciklama = offerAciklama;
    }

    public String getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(String offerDate) {
        this.offerDate = offerDate;
    }

    public String getOfferUserId() {
        return offerUserId;
    }

    public void setOfferUserId(String offerUserId) {
        this.offerUserId = offerUserId;
    }
}
