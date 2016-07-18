package com.example.deadlydragger.neartaxi;

/**
 * Created by deadlydragger on 7/14/16.
 */
public class Taxi {
    public double lat;
    public double lng;
    public String title;
    public int image;

    public double getLat(){
        return lat;
    }
    public double getLng(){
        return lng;
    }
    public void setLat(double lat){
        this.lat=lat;
    }
    public void setLng(double lng){
        this.lng=lng;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public int getImage(){
        return image;
    }
    public void setImage(int image){
         this.image=image;
    }
}
