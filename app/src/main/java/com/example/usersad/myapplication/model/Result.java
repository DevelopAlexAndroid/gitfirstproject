package com.example.usersad.myapplication.model;

import com.example.usersad.myapplication.model.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usersad on 21.12.2017.
 */

public class Result {

    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("data")
    @Expose
    private Data data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
