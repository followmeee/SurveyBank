package com.example.surveybank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Anket implements Serializable {
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Editor_ID")
    @Expose
    private Integer editorID;
    @SerializedName("Baslik")
    @Expose
    private String baslik;
    @SerializedName("Aciklama")
    @Expose
    private String aciklama;
    @SerializedName("Konu")
    @Expose
    private String konu;
    @SerializedName("Olusturma_Tarih")
    @Expose
    private String olusturmaTarih;
    @SerializedName("Kapanis_Tarih")
    @Expose
    private String kapanisTarih;
    @SerializedName("Sorular")
    @Expose
    private List<Sorular> sorular = null;
    @SerializedName("Anketor_ID")
    @Expose
    private Integer anketorID;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getEditorID() {
        return editorID;
    }

    public void setEditorID(Integer editorID) {
        this.editorID = editorID;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public String getOlusturmaTarih() {
        return olusturmaTarih;
    }

    public void setOlusturmaTarih(String olusturmaTarih) {
        this.olusturmaTarih = olusturmaTarih;
    }

    public String getKapanisTarih() {
        return kapanisTarih;
    }

    public void setKapanisTarih(String kapanisTarih) {
        this.kapanisTarih = kapanisTarih;
    }

    public List<Sorular> getSorular() {
        return sorular;
    }

    public void setSorular(List<Sorular> sorular) {
        this.sorular = sorular;
    }

    public Integer getAnketorID() {
        return anketorID;
    }

    public void setAnketorID(Integer anketorID) {
        this.anketorID = anketorID;
    }
}
