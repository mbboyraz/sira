package com.ecommerce.bitirme.ecommerce.Classes;

/**
 * Created by mbura on 5.01.2018.
 */

public class OfferHouse {
    private String offerFiyat;
    private String offerm2;
    private String offerAciklama;
    private String offerDate;
    private String offerUserId;

    public OfferHouse(String offerFiyat, String offerm2, String offerAciklama, String offerDate, String offerUserId) {
        this.offerFiyat = offerFiyat;
        this.offerm2 = offerm2;
        this.offerAciklama = offerAciklama;
        this.offerDate = offerDate;
        this.offerUserId = offerUserId;
    }

    public OfferHouse() {
    }

    public String getOfferFiyat() {
        return offerFiyat;
    }

    public void setOfferFiyat(String offerFiyat) {
        this.offerFiyat = offerFiyat;
    }

    public String getOfferm2() {
        return offerm2;
    }

    public void setOfferm2(String offerm2) {
        this.offerm2 = offerm2;
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
