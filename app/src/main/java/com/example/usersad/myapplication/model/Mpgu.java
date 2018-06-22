package com.example.usersad.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by usersad on 25.12.2017.
 */

public class Mpgu {
    @SerializedName("num")
    @Expose
    private int num;
    @SerializedName("bmpgu_mon_num")
    @Expose
    private int bmpguMonNum;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("tag_data")
    @Expose
    private Value values;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getBmpguMonNum() {
        return bmpguMonNum;
    }

    public void setBmpguMonNum(int bmpguMonNum) {
        this.bmpguMonNum = bmpguMonNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Value getValues() {
        return values;
    }

    public void setValues(Value values) {
        this.values = values;
    }
}
