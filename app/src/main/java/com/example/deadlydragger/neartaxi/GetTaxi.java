package com.example.deadlydragger.neartaxi;

/**
 * Created by deadlydragger on 7/8/16.
 */
public class GetTaxi {
    public String cell_phone;
    public String distance;
    public Double loc_lat;
    public Double loc_lng;
    public String merch_name;

    public String getCell_phone() {
        return cell_phone;
    }

    public String getDistance() {
        return distance;
    }

    public Double getLoc_lat() {
        return loc_lat;
    }

    public Double getLoc_lng() {
        return loc_lng;
    }

    public String getMerch_name() {
        return merch_name;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setLoc_lat(Double loc_lat) {
        this.loc_lat = loc_lat;
    }

    public void setLoc_lng(Double loc_lng) {
        this.loc_lng = loc_lng;
    }

    public void setMerch_name(String merch_name) {
        this.merch_name = merch_name;
    }

}
