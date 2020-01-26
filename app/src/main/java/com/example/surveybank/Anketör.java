package com.example.surveybank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Anketör {
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Adi")
    @Expose
    private String adi;
    @SerializedName("Soyadi")
    @Expose
    private String soyadi;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Cep_Tel")
    @Expose
    private String cepTel;
    @SerializedName("Bolge_ID")
    @Expose
    private Integer bolgeID;
    @SerializedName("Sehir_ID")
    @Expose
    private Integer sehirID;
    @SerializedName("Cinsiyet")
    @Expose
    private String cinsiyet;
    @SerializedName("Kullanici_Adi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("Kullanici_Sifre")
    @Expose
    private String kullaniciSifre;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCepTel() {
        return cepTel;
    }

    public void setCepTel(String cepTel) {
        this.cepTel = cepTel;
    }

    public Integer getBolgeID() {
        return bolgeID;
    }

    public void setBolgeID(Integer bolgeID) {
        this.bolgeID = bolgeID;
    }

    public Integer getSehirID() {
        return sehirID;
    }

    public void setSehirID(Integer sehirID) {
        this.sehirID = sehirID;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getKullaniciSifre() {
        return kullaniciSifre;
    }

    public void setKullaniciSifre(String kullaniciSifre) {
        this.kullaniciSifre = kullaniciSifre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Anketör{" +
                "iD=" + iD +
                ", adi='" + adi + '\'' +
                ", soyadi='" + soyadi + '\'' +
                ", email='" + email + '\'' +
                ", cepTel='" + cepTel + '\'' +
                ", bolgeID=" + bolgeID +
                ", sehirID=" + sehirID +
                ", cinsiyet='" + cinsiyet + '\'' +
                ", kullaniciAdi='" + kullaniciAdi + '\'' +
                ", kullaniciSifre='" + kullaniciSifre + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
