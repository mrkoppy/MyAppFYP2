package com.example.mrkoppy.myappfyp;

public class Post_availabletrip {

    public String From;
    public String To;
    public String Date;
    public String Est;

    /*Constructor for data to be passed from database*/
    public Post_availabletrip(String From,
                String To, String Date, String Est) {
        this.From = From;
        this.To = To;
        this.Date = Date;
        this.Est = Est;
    }

}