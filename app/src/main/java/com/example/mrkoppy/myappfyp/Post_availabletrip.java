package com.example.mrkoppy.myappfyp;

import java.io.Serializable;

public class Post_availabletrip implements Serializable{

    public String From;
    public String To;
    public String Date;
    public String Est;
    public String Price;
    public String Seats_left;
    public String Gender;
    public String Name;

    /*Constructor for data to be passed from database*/
    public Post_availabletrip(String From, String To, String Date, String Est, String Price, String Seats_left, String Gender, String
                              Name) {
        this.From = From;
        this.To = To;
        this.Date = Date;
        this.Est = Est;
        this.Price = Price;
        this.Seats_left = Seats_left;
        this.Gender = Gender;
        this.Name = Name;
    }

}