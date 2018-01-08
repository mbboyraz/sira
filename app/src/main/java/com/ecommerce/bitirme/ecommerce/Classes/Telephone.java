package com.ecommerce.bitirme.ecommerce.Classes;

/**
 * Created by mbura on 8.01.2018.
 */

public class Telephone {
    private String telMarka;
    private String telModel;
    private String telPriceMin;
    private String telPriceMax;
    private String telDate;
    private String telOS;
    private String telRam;
    private String telMemory;
    private String telCameraRear;
    private String telCameraFront;
    private String telColor;
    private String telState;
    private String telCity;
    private String telAciklama;
    private String userId;


    public Telephone(String telMarka, String telModel, String telPriceMin, String telPriceMax, String telDate, String telOS, String telRam, String telMemory, String telCameraRear, String telCameraFront, String telColor, String telState, String telCity, String telAciklama, String userId) {
        this.telMarka = telMarka;
        this.telModel = telModel;
        this.telPriceMin = telPriceMin;
        this.telPriceMax = telPriceMax;
        this.telDate = telDate;
        this.telOS = telOS;
        this.telRam = telRam;
        this.telMemory = telMemory;
        this.telCameraRear = telCameraRear;
        this.telCameraFront = telCameraFront;
        this.telColor = telColor;
        this.telState = telState;
        this.telCity = telCity;
        this.telAciklama = telAciklama;
        this.userId = userId;
    }

    public Telephone() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTelAciklama() {
        return telAciklama;
    }

    public void setTelAciklama(String telAciklama) {
        this.telAciklama = telAciklama;
    }

    public String getTelCity() {
        return telCity;
    }

    public void setTelCity(String telCity) {
        this.telCity = telCity;
    }

    public String getTelMarka() {
        return telMarka;
    }

    public void setTelMarka(String telMarka) {
        this.telMarka = telMarka;
    }

    public String getTelModel() {
        return telModel;
    }

    public void setTelModel(String telModel) {
        this.telModel = telModel;
    }

    public String getTelPriceMin() {
        return telPriceMin;
    }

    public void setTelPriceMin(String telPriceMin) {
        this.telPriceMin = telPriceMin;
    }

    public String getTelPriceMax() {
        return telPriceMax;
    }

    public void setTelPriceMax(String telPriceMax) {
        this.telPriceMax = telPriceMax;
    }

    public String getTelDate() {
        return telDate;
    }

    public void setTelDate(String telDate) {
        this.telDate = telDate;
    }

    public String getTelOS() {
        return telOS;
    }

    public void setTelOS(String telOS) {
        this.telOS = telOS;
    }

    public String getTelRam() {
        return telRam;
    }

    public void setTelRam(String telRam) {
        this.telRam = telRam;
    }

    public String getTelMemory() {
        return telMemory;
    }

    public void setTelMemory(String telMemory) {
        this.telMemory = telMemory;
    }

    public String getTelCameraRear() {
        return telCameraRear;
    }

    public void setTelCameraRear(String telCameraRear) {
        this.telCameraRear = telCameraRear;
    }

    public String getTelCameraFront() {
        return telCameraFront;
    }

    public void setTelCameraFront(String telCameraFront) {
        this.telCameraFront = telCameraFront;
    }

    public String getTelColor() {
        return telColor;
    }

    public void setTelColor(String telColor) {
        this.telColor = telColor;
    }

    public String getTelState() {
        return telState;
    }

    public void setTelState(String telState) {
        this.telState = telState;
    }
}
