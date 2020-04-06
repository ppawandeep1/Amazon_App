package com.example.amazonapp.Models;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ProfilePageModel  {
    public String CustomerId;
    public String Fname;
    public String Lname;
    public String Email;
    public String PhoneNumber;
    public String CountryId;
    public String ProvinceId;
    public String City;
    public String PostalCode;
    public String Address;
    public String Password;
    public String CompanyId;


    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getFname() {

        return Fname;
    }

    public void setFname(String fname)
    {
        Fname = fname;
    }

    public String getLname()
    {
        return Lname;
    }

    public void setLname(String lname)
    {
        Lname = lname;
    }

    public String getEmail() {

        return Email;
    }

    public void setEmail(String email) {

        Email = email;
    }

    public String getPhoneNumber() {

        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        PhoneNumber = phoneNumber;
    }

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public String getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(String provinceId) {
        ProvinceId = provinceId;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }
}
