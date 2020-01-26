package com.example.surveybank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Sorular implements Serializable {
    @SerializedName("soruid")
    @Expose
    private Integer soruid;
    @SerializedName("soru")
    @Expose
    private String soru;
    @SerializedName("secenekid")
    @Expose
    private List<Integer> secenekid = null;
    @SerializedName("secenek")
    @Expose
    private List<String> secenek = null;

    public Integer getSoruid() {
        return soruid;
    }

    public void setSoruid(Integer soruid) {
        this.soruid = soruid;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public List<Integer> getSecenekid() {
        return secenekid;
    }

    public void setSecenekid(List<Integer> secenekid) {
        this.secenekid = secenekid;
    }

    public List<String> getSecenek() {
        return secenek;
    }

    public void setSecenek(List<String> secenek) {
        this.secenek = secenek;
    }

}
