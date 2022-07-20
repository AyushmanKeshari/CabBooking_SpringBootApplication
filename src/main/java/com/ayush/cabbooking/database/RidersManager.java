package com.ayush.cabbooking.database;

import com.ayush.cabbooking.exceptions.RiderAlreadyExistException;
import com.ayush.cabbooking.exceptions.RiderNotFoundException;
import com.ayush.cabbooking.model.Rider;

import java.util.HashMap;
import java.util.Map;

public class RidersManager {
    Map<String, Rider> riders = new HashMap<>();

    public void createRider(Rider rider) {
        if (riders.containsKey(rider.getId())) {
            throw new RiderAlreadyExistException();
        }
        riders.put(rider.getId(), rider);
    }

    public Rider getRider(String id) {
        if (!riders.containsKey(id)) {
            throw new RiderNotFoundException();
        } else {
            return riders.get(id);
        }
    }
}
