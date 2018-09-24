package com.example.mrkoppy.myappfyp;

public class Post_alluser {
    public String Name;
    public String StudentID;
    public String Mobile;
    public String Email;
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
}
