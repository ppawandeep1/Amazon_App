package com.example.amazonapp.Models;

public class ProfilePageModel {
    public String Firstname;
    public String Lastname;
    public String EAdrress;
    public String Phnumber;
    public String Countryid;
    public String Provinceid;
    public String city;
    public String postalcode;
    public String address;

    public ProfilePageModel(String firstname, String lastname, String EAdrress, String phnumber, String countryid, String provinceid, String city, String postalcode, String address) {
        Firstname = firstname;
        Lastname = lastname;
        this.EAdrress = EAdrress;
        Phnumber = phnumber;
        Countryid = countryid;
        Provinceid = provinceid;
        this.city = city;
        this.postalcode = postalcode;
        this.address = address;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEAdrress() {
        return EAdrress;
    }

    public void setEAdrress(String EAdrress) {
        this.EAdrress = EAdrress;
    }

    public String getPhnumber() {
        return Phnumber;
    }

    public void setPhnumber(String phnumber) {
        Phnumber = phnumber;
    }

    public String getCountryid() {
        return Countryid;
    }

    public void setCountryid(String countryid) {
        Countryid = countryid;
    }

    public String getProvinceid() {
        return Provinceid;
    }

    public void setProvinceid(String provinceid) {
        Provinceid = provinceid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
