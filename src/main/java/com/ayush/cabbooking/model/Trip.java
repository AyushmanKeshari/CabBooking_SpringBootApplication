package com.ayush.cabbooking.model;

import lombok.ToString;

enum TripStatus {
    IN_PROGRESS,
    FINISHED
}

@ToString
public class Trip {
    private Cab cab;
    private Rider rider;
    private TripStatus tripStatus;
    private Double price;
    private Location fromPoint;
    private Location toPoint;

    public Trip(Cab cab, Rider rider, Double price, Location fromPoint, Location toPoint) {
        this.cab = cab;
        this.rider = rider;
        this.price = price;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.tripStatus = TripStatus.IN_PROGRESS;
    }

    public void endTrip() {
        this.tripStatus = TripStatus.FINISHED;
    }

}
