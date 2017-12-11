package com.ecommerce.bitirme.ecommerce.Activity;

/**
 * Created by Sedat Er on 24.11.2017.
 */


public class katagori {
    public String KatagoriName;
    public String KatagoriAltName;

    public katagori(String KatagoriName, String KatagoriAltName) {
        this.KatagoriName=KatagoriName;
        this.KatagoriAltName = KatagoriAltName;
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
