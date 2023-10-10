package com.einvoicing.applicaiton.data;

import jakarta.persistence.Entity;

@Entity
public class Customer extends AbstractEntity {

    private String source;
    private String idType;
    private String idNumber;
    private String nameEng;
    private String nameArb;
    private String buildingNumber;
    private String unitNumber;
    private String additionalNumber;
    private String streetEng;
    private String streetArb;
    private String distrcitEng;
    private String districtArb;
    private String cityEng;
    private String cityArb;
    private String country;
    private String postalCode;
    private String vatNumber;
    private String email;
    private String phone;

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public String getIdNumber() {
        return idNumber;
    }
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    public String getNameEng() {
        return nameEng;
    }
    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }
    public String getNameArb() {
        return nameArb;
    }
    public void setNameArb(String nameArb) {
        this.nameArb = nameArb;
    }
    public String getBuildingNumber() {
        return buildingNumber;
    }
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
    public String getUnitNumber() {
        return unitNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getAdditionalNumber() {
        return additionalNumber;
    }
    public void setAdditionalNumber(String additionalNumber) {
        this.additionalNumber = additionalNumber;
    }
    public String getStreetEng() {
        return streetEng;
    }
    public void setStreetEng(String streetEng) {
        this.streetEng = streetEng;
    }
    public String getStreetArb() {
        return streetArb;
    }
    public void setStreetArb(String streetArb) {
        this.streetArb = streetArb;
    }
    public String getDistrcitEng() {
        return distrcitEng;
    }
    public void setDistrcitEng(String distrcitEng) {
        this.distrcitEng = distrcitEng;
    }
    public String getDistrictArb() {
        return districtArb;
    }
    public void setDistrictArb(String districtArb) {
        this.districtArb = districtArb;
    }
    public String getCityEng() {
        return cityEng;
    }
    public void setCityEng(String cityEng) {
        this.cityEng = cityEng;
    }
    public String getCityArb() {
        return cityArb;
    }
    public void setCityArb(String cityArb) {
        this.cityArb = cityArb;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getVatNumber() {
        return vatNumber;
    }
    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
