package com.example.mrkoppy.myappfyp;

import java.io.Serializable;

public class Post_admincomments implements Serializable {
    public String UserID;
    public String RouteID;
    public String Overallstar;
    public String Comment;

    public Post_admincomments(String UserID, String RouteID, String Overallstar, String Comment){
        this.UserID = UserID;
        this.RouteID = RouteID;
        this.Overallstar = Overallstar;
        this.Comment = Comment;
    }
}
