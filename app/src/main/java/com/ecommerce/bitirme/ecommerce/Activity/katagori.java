package com.ecommerce.bitirme.ecommerce.Activity;

/**
 * Created by Sedat Er on 24.11.2017.
 */


public class katagori {
    private String katagoriName;
    private String katagoriAltName;
    private String katagoriDate;
    private String katagoriPhoto;
    private String katagoriIlanid;
    private String id;


    private String teklifTur;

    public katagori(String katagoriName, String katagoriAltName, String katagoriDate, String katagoriPhoto, String katagoriIlanid, String id, String teklifTur) {
        this.katagoriName = katagoriName;
        this.katagoriAltName = katagoriAltName;
        this.katagoriDate = katagoriDate;
        this.katagoriPhoto = katagoriPhoto;
        this.katagoriIlanid = katagoriIlanid;
        this.id = id;
        this.teklifTur = teklifTur;
    }

    public String getKatagoriName() {
        return katagoriName;
    }

    public void setKatagoriName(String katagoriName) {
        this.katagoriName = katagoriName;
    }

    public String getKatagoriAltName() {
        return katagoriAltName;
    }

    public void setKatagoriAltName(String katagoriAltName) {
        this.katagoriAltName = katagoriAltName;
    }

    public String getTeklifTur() {
        return teklifTur;
    }

    public void setTeklifTur(String teklifTur) {
        this.teklifTur = teklifTur;
    }

    public String getKatagoriDate() {
        return katagoriDate;
    }

    public void setKatagoriDate(String katagoriDate) {
        this.katagoriDate = katagoriDate;
    }

    public String getKatagoriPhoto() {
        return katagoriPhoto;
    }

    public void setKatagoriPhoto(String katagoriPhoto) {
        this.katagoriPhoto = katagoriPhoto;
    }

    public String getKatagoriIlanid() {
        return katagoriIlanid;
    }

    public void setKatagoriIlanid(String katagoriIlanid) {
        this.katagoriIlanid = katagoriIlanid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
