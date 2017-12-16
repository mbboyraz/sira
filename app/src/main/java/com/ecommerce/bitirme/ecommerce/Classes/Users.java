package com.ecommerce.bitirme.ecommerce.Classes;

/**
 * Created by Sedat Er on 16.12.2017.
 */

public class Users {
    public String usersName;
    public String usersEmail;
    public String usersPhotourl;
    public String usersTel;

    public Users(String usersname, String usersemail, String usersphotourl, String userstel) {
        this.usersName = usersname;
        this.usersEmail = usersemail;
        this.usersPhotourl = usersphotourl;
        this.usersTel = userstel;


    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUsersEmail() {
        return usersEmail;
    }

    public void setUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
    }

    public String getUsersPhotourl() {
        return usersPhotourl;
    }

    public void setUsersPhotourl(String usersPhotourl) {
        this.usersPhotourl = usersPhotourl;
    }

    public String getUsersTel() {
        return usersTel;
    }

    public void setUsersTel(String usersTel) {
        this.usersTel = usersTel;
    }
}
