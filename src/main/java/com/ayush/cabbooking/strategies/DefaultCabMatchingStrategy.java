package com.ayush.cabbooking.strategies;

import com.ayush.cabbooking.model.Cab;
import com.ayush.cabbooking.model.Location;
import com.ayush.cabbooking.model.Rider;
import lombok.NonNull;

import java.util.List;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {
    @Override
    public Cab matchCabToRider(@NonNull Rider rider, @NonNull List<Cab> candidateCabs
            , @NonNull Location fromPoint, @NonNull Location toPoint) {
        if (candidateCabs.isEmpty()) return null;
        return candidateCabs.get(0);
    }
}
