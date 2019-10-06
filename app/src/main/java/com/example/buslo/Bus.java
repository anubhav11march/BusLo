package com.example.buslo;

public class Bus {
    private String avgtime, cap, lat, lon, lstart, occ, routedistance, stops, status;
    public Bus(){

    }

    public Bus(String avgtime, String cap, String lat, String lon, String lstart, String occ, String routedistance, String stops, String status){
        this.avgtime = avgtime;
        this.cap = cap;
        this.lon = lon;
        this.status =status;
        this.lat = lat;
        this.lstart = lstart;
        this.occ = occ;

        this.routedistance = routedistance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getAvgtime() {
        return avgtime;
    }

    public String getCap() {
        return cap;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getLstart() {
        return lstart;
    }

    public String getOcc() {
        return occ;
    }

    public String getRoutedistance() {
        return routedistance;
    }

    public void setAvgtime(String avgtime) {
        this.avgtime = avgtime;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLstart(String lstart) {
        this.lstart = lstart;
    }

    public void setOcc(String occ) {
        this.occ = occ;
    }

    public void setRoutedistance(String routedistance) {
        this.routedistance = routedistance;
    }
}
