package com.entity;

public class Daerah {
    private int postalCode;
    private String district;
    private String city;
    private String country;

    public Daerah(){
        
    }
    public Daerah(int postalCode, String district, String city, String country){
        this.postalCode = postalCode;
        this.district = district;
        this.city = city;
        this.country = country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    
}
