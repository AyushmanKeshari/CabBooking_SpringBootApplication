package com.ayush.cabbooking.strategies;

import com.ayush.cabbooking.model.Cab;
import com.ayush.cabbooking.model.Location;
import com.ayush.cabbooking.model.Rider;

import java.util.List;

public interface CabMatchingStrategy {
    Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}
