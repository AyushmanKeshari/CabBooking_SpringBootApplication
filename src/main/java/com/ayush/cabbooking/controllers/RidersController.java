package com.ayush.cabbooking.controllers;

import com.ayush.cabbooking.database.RidersManager;
import com.ayush.cabbooking.database.TripsManager;
import com.ayush.cabbooking.model.Location;
import com.ayush.cabbooking.model.Rider;
import com.ayush.cabbooking.model.Trip;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@AllArgsConstructor
public class RidersController {
    private RidersManager ridersManager;
    private TripsManager tripsManager;

    @RequestMapping(value = "/register/rider", method = RequestMethod.POST)
    public ResponseEntity createRider(String riderId, String riderName) {
        ridersManager.createRider(new Rider(riderId, riderName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity book(String riderId, Double sourceX, Double sourceY, Double destX, Double destY) {
        tripsManager.createTrip(ridersManager.getRider(riderId), new Location(sourceX, sourceY), new Location(destX, destY));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity fetchHistory(String riderID) {
        List<Trip> trips = tripsManager.getHistory(ridersManager.getRider(riderID));
        return ResponseEntity.ok(trips);
    }

}
