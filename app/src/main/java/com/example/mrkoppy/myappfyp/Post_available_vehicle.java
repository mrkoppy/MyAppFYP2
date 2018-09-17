package com.example.mrkoppy.myappfyp;

public class Post_available_vehicle {

    private String Carplate;
    private String Carseat;
    /*public String Carplate;
    public String Carseat;
    public Post_available_vehicle(String Carplate, String Carseat){
        this.Carplate = Carplate;
        this.Carseat = Carseat;
    }*/

    Post_available_vehicle(String Carplate, String Carseat){
        this.Carplate = Carplate;
        this.Carseat = Carseat;
    }

    String getCarplate(){
        return Carplate;
    }

    void setCarplate(String Carplate){
        this.Carplate = Carplate;
    }

    String getCarseat(){
        return Carseat;
    }

    void setCarseat(String Carseat){
        this.Carseat = Carseat;
    }
}
