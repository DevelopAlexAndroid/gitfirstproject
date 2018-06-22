package com.example.usersad.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usersad on 25.12.2017.
 */

public class Value {
    @SerializedName("steam")
    @Expose
    private String steam;
    @SerializedName("p_steam")
    @Expose
    private String pSteam;
    @SerializedName("gas")
    @Expose
    private String gas;
    @SerializedName("water")
    @Expose
    private String water;
    @SerializedName("alpha")
    @Expose
    private double alpha;
    @SerializedName("datetime")
    @Expose
    private String datetime;

    private int idButton;

    public int getIdButton() {
        return idButton;
    }

    public void setIdButton(int idButton) {this.idButton = idButton;}

    public String getSteam() {
        return steam;
    }

    public void setSteam(String steam) {
        this.steam = steam;
    }

    public String getpSteam() {
        return pSteam;
    }

    public void setpSteam(String pSteam) {
        this.pSteam = pSteam;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
