package com.ayush.cabbooking.model;

import lombok.Getter;
import lombok.Setter;

//Same as driver.  Cab and driver is 1 entity.
@Getter
public class Cab {
    private String cabId;
    private String driverName;

    @Setter
    private Trip trip;
    @Setter
    private Location currentLocation;
    @Setter
    private boolean isAvailable;

    public Cab(String cabId, String driverName) {
        this.cabId = cabId;
        this.driverName = driverName;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "cabId='" + cabId + '\'' +
                ", driverName='" + driverName + '\'' +
                ", currentLocation=" + currentLocation +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
