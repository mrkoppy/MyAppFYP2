package com.example.mrkoppy.myappfyp;

import java.io.Serializable;

public class Post_availabletrip implements Serializable{

    public String From;
    public String To;
    public String Date;
    public String Est;
    public String Price;

    /*Constructor for data to be passed from database*/
    public Post_availabletrip(String From, String To, String Date, String Est, String Price) {
        this.From = From;
        this.To = To;
        this.Date = Date;
        this.Est = Est;
        this.Price = Price;
    }

}