package com.ayush.cabbooking.strategies;

import com.ayush.cabbooking.model.Location;

public interface PricingStrategy {
    Double findPrice(Location fromPoint, Location toPoint);
}
