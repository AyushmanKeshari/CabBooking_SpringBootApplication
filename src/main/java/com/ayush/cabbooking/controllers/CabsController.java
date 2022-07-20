package com.ayush.cabbooking.controllers;

import com.ayush.cabbooking.database.CabsManager;
import com.ayush.cabbooking.database.TripsManager;
import com.ayush.cabbooking.model.Cab;
import com.ayush.cabbooking.model.Location;
import com.ayush.cabbooking.model.Trip;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class CabsController {

    private CabsManager cabsManager;
    private TripsManager tripsManager;

    @RequestMapping(value = "/register/cab", method = RequestMethod.POST)
    public ResponseEntity registerCab(String cabID, String driverName) {
        cabsManager.createCab(new Cab(cabID, driverName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/location", method = RequestMethod.POST)
    public ResponseEntity updateLocation(String cabID, Double x, Double y) {
        cabsManager.updateCabLocation(cabID, new Location(x, y));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/update/cab/availability", method = RequestMethod.POST)
    public ResponseEntity updateCabAvailability(String cabId, boolean isAvailable) {
        cabsManager.updateCabAvailability(cabId, isAvailable);
        return ResponseEntity.ok("");
    }

    public ResponseEntity endTrip(String cabID) {
        tripsManager.endTrip(cabsManager.getCab(cabID));
        return ResponseEntity.ok("");
    }

}
