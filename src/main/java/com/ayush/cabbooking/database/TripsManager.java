package com.ayush.cabbooking.database;

import com.ayush.cabbooking.exceptions.TripsNotFoundException;
import com.ayush.cabbooking.model.Cab;
import com.ayush.cabbooking.model.Location;
import com.ayush.cabbooking.model.Rider;
import com.ayush.cabbooking.model.Trip;
import com.ayush.cabbooking.strategies.CabMatchingStrategy;
import com.ayush.cabbooking.strategies.PricingStrategy;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripsManager {
    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;

    private Map<String, List<Trip>> trips = new HashMap<>();

    private CabsManager cabsManager;
    private RidersManager ridersManager;

    private CabMatchingStrategy cabMatchingStrategy;
    private PricingStrategy pricingStrategy;


    public TripsManager(
            @NonNull CabsManager cabsManager,
            @NonNull RidersManager ridersManager,
            @NonNull CabMatchingStrategy cabMatchingStrategy,
            @NonNull PricingStrategy pricingStrategy) {
        this.cabsManager = cabsManager;
        this.ridersManager = ridersManager;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void createTrip(@NonNull Rider rider, @NonNull Location fromPoint, @NonNull Location toPoint) {
        List<Cab> cabList = cabsManager.getCabs(fromPoint, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);

        final List<Cab> closeByAvailableCabs = cabList.stream()
                .filter(cab -> cab.getTrip() == null)
                .collect(Collectors.toList());

        Cab cab = cabMatchingStrategy.matchCabToRider(rider, closeByAvailableCabs, fromPoint, toPoint);
        Double price = pricingStrategy.findPrice(fromPoint, toPoint);

        Trip trip = new Trip(cab, rider, price, fromPoint, toPoint);

        if (!trips.containsKey(rider.getId())) {
            trips.put(rider.getId(), new ArrayList<>());
        }
        trips.get(rider.getId()).add(trip);
        cab.setTrip(trip);
    }

    public List<Trip> getHistory(@NonNull Rider rider) {
        if (trips.containsKey(rider.getId())) return trips.get(rider.getId());
        else throw new TripsNotFoundException();
    }

    public void endTrip(@NonNull Cab cab) {
        if (cab.getTrip() == null) {
            throw new TripsNotFoundException();
        }
        cab.getTrip().endTrip();
        cab.setTrip(null);
    }

}
