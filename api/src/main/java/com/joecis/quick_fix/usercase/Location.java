package com.joecis.quick_fix.usercase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Location {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    Long id;
    
    @Column(nullable = false)
    private Double lat;
    @Column(nullable = false)
    private Double lon;

    public Location(Long id, Double lat, Double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public Location() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", lat=" + lat + ", lon=" + lon + "]";
    }
}

