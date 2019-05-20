package com.example.mevin.volleytest;

/**
 * Created by Mevin on 3/25/2019.
 */


public class Item {

    public String name;
    public String pkd;
    public  String mrp;
    public String reason;
    public String qty;
    public Item() {

    }

    public String getPkd() {
        return pkd;
    }

    public void setPkd(String pkd) {
        this.pkd = pkd;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}