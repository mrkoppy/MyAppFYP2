package com.example.mrkoppy.myappfyp;

import java.io.Serializable;

public class Post_alluser implements Serializable {
    public String Name;
    public String StudentID;
    public String Mobile;
    public String Email;
    public String Postcode;
    public String Carplate;
    public String Carseats;
    public String Gender;
    public int id;

    public Post_alluser(String Name, String StudentID, String Mobile,String Email){
        this.Name = Name;
        this.StudentID = StudentID;
        this.Mobile = Mobile;
        this.Email = Email;

    }

    public Post_alluser(int id, String Name, String StudentID,String Email){
        this.id = id;
        this.Name = Name;
        this.StudentID = StudentID;
        this.Email = Email;

    }

    public Post_alluser(String Name, String StudentID,String Email){
        this.Name = Name;
        this.StudentID = StudentID;
        this.Email = Email;

    }

    public Post_alluser(int id, String Name, String Gender, String Email, String Mobile, String Postcode, String StudentID,
                        String Carplate, String Carseats){
        this.id = id;
        this.Name = Name;
        this.Gender = Gender;
        this.Email = Email;
        this.Mobile = Mobile;
        this.Postcode = Postcode;
        this.StudentID = StudentID;
        this.Carplate = Carplate;
        this.Carseats = Carseats;

    }
}
