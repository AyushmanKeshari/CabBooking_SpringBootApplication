package com.ayush.cabbooking.database;

import com.ayush.cabbooking.exceptions.CabAlreadyExistException;
import com.ayush.cabbooking.exceptions.CabNotFoundException;
import com.ayush.cabbooking.model.Cab;
import com.ayush.cabbooking.model.Location;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabsManager {
    Map<String, Cab> cabs = new HashMap<>();

    public void createCab(Cab cab) {
        if (cabs.containsKey(cab.getCabId())) {
            throw new CabAlreadyExistException();
        } else {
            cabs.put(cab.getCabId(), cab);
        }
    }

    public Cab getCab(String cabId) {
        if (cabs.containsKey(cabId)) {
            return cabs.get(cabId);
        } else {
            throw new CabNotFoundException();
        }
    }

    public void updateCabLocation(String cabId, Location newLocation) {
        if (!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        cabs.get(cabId).setCurrentLocation(newLocation);
    }

    public void updateCabAvailability(String cabId, boolean newAvailability) {
        if (!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        cabs.get(cabId).setAvailable(newAvailability);
    }

    public List<Cab> getCabs(@NonNull Location fromPoint, final Double distance) {
        List<Cab> result = new ArrayList<>();

        for (Cab cab : cabs.values()) {
            if (cab.isAvailable() && fromPoint.distance(cab.getCurrentLocation()) <= distance) {
                result.add(cab);
            }
        }
        return result;
    }


}
