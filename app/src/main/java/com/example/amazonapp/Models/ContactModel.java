package com.example.amazonapp.Models;

public class ContactModel {
    public String Company_name;
    public String website;
    public String Logo;
    public String Address1;
    public String Address2;
    public String City;
    public String CountryID;
    public String ProvinceId;
    public String PostalCode;
    public String CurrencyId;

    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountryID() {
        return CountryID;
    }

    public void setCountryID(String countryID) {
        CountryID = countryID;
    }

    public String getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(String provinceId) {
        ProvinceId = provinceId;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(String currencyId) {
        CurrencyId = currencyId;
    }
}
