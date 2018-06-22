package com.example.usersad.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.Query;

/**
 * Created by usersad on 21.12.2017.
 */

public class Data {

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("ptv")
    @Expose
    private List<Ptv> ptv ;

    @SerializedName("values")
    @Expose
    private List<Mpgu> mpgu;

    public List<Mpgu> getMpgu() {return mpgu;}

    public void setMpgu(List<Mpgu> mpgus) {
        this.mpgu = mpgu;
    }

    public List<Ptv> getPtv() {
        return ptv;
    }

    public void setPtv(List<Ptv> ptv) {
        this.ptv = ptv;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
