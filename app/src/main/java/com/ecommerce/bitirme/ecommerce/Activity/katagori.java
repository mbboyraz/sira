package com.ecommerce.bitirme.ecommerce.Activity;

/**
 * Created by Sedat Er on 24.11.2017.
 */


public class katagori {
    public String KatagoriName;
    public String KatagoriAltName;
    public String id;

    public katagori(String KatagoriName, String KatagoriAltName, String id) {
        this.KatagoriName=KatagoriName;
        this.KatagoriAltName = KatagoriAltName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKatagoriName() {
        return KatagoriName;
    }

    public void setKatagoriName(String KatagoriName) {
        this.KatagoriName = KatagoriName;
    }


    public String getKatagoriAltName() {
        return KatagoriAltName;
    }

    public void setKatagoriAltName(String katagoriAltName) {
        KatagoriAltName = katagoriAltName;
    }
}
