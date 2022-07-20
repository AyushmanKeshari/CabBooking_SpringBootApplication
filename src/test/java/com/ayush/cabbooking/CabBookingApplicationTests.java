package com.ayush.cabbooking;

import com.ayush.cabbooking.controllers.CabsController;
import com.ayush.cabbooking.controllers.RidersController;
import com.ayush.cabbooking.database.CabsManager;
import com.ayush.cabbooking.database.RidersManager;
import com.ayush.cabbooking.database.TripsManager;
import com.ayush.cabbooking.exceptions.CabAlreadyExistException;
import com.ayush.cabbooking.exceptions.CabNotFoundException;
import com.ayush.cabbooking.exceptions.RiderAlreadyExistException;
import com.ayush.cabbooking.exceptions.RiderNotFoundException;
import com.ayush.cabbooking.strategies.CabMatchingStrategy;
import com.ayush.cabbooking.strategies.DefaultCabMatchingStrategy;
import com.ayush.cabbooking.strategies.DefaultPricingStrategy;
import com.ayush.cabbooking.strategies.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CabBookingApplicationTests {

    CabsController cabsController;
    RidersController ridersController;

    @BeforeEach
    public void setUp() {
        CabsManager cabsManager = new CabsManager();
        RidersManager ridersManager = new RidersManager();

        CabMatchingStrategy cabMatchingStrategy = new DefaultCabMatchingStrategy();
        PricingStrategy pricingStrategy = new DefaultPricingStrategy();


        TripsManager tripsManager = new TripsManager(cabsManager, ridersManager, cabMatchingStrategy, pricingStrategy);


        cabsController = new CabsController(cabsManager, tripsManager);
        ridersController = new RidersController(ridersManager, tripsManager);
    }

    @Test
    void cabBookingFlow() {

		String r1 = "r1";
		ridersController.createRider(r1, "rider1");
		String r2 = "r2";
		ridersController.createRider(r2, "rider2");
		String r3 = "r3";
		ridersController.createRider(r3, "rider3");
		String r4 = "r4";
		ridersController.createRider(r4, "rider4");


		String c1 = "c1";
		cabsController.registerCab(c1, "driver1");
		String c2 = "c2";
		cabsController.registerCab(c2, "driver2");
		String c3 = "c3";
		cabsController.registerCab(c3, "driver3");
		String c4 = "c4";
		cabsController.registerCab(c4, "driver4");
		String c5 = "c5";
		cabsController.registerCab(c5, "driver5");

		cabsController.updateLocation(c1, 1.0, 1.0);
		cabsController.updateLocation(c2, 2.0, 2.0);
		cabsController.updateLocation(c3, 100.0, 100.0);
		cabsController.updateLocation(c4, 110.0, 110.0);
		cabsController.updateLocation(c5, 4.0, 4.0);


		cabsController.updateCabAvailability(c2, false);
		cabsController.updateCabAvailability(c4, false);

		ridersController.book(r1, 0.0, 0.0, 500.0, 500.0);
		ridersController.book(r2, 0.0, 0.0, 500.0, 500.0);

		System.out.println("\n### Printing current trips for r1 and r2");
		System.out.println(ridersController.fetchHistory(r1).getBody());
		System.out.println(ridersController.fetchHistory(r2).getBody());

		cabsController.updateLocation(c5, 50.0, 50.0);

		System.out.println("\n### Printing current trips for r1 and r2");
		System.out.println(ridersController.fetchHistory(r1).getBody());
		System.out.println(ridersController.fetchHistory(r2).getBody());

		cabsController.endTrip(c5);

		System.out.println("\n### Printing current trips for r1 and r2");
		System.out.println(ridersController.fetchHistory(r1).getBody());
		System.out.println(ridersController.fetchHistory(r2).getBody());


//		assertThrows(CabNotFoundException.class, () -> {
//			ridersController.book(r3, 0.0, 0.0, 500.0, 500.0);
//		});

		ridersController.book(r4, 48.0, 48.0, 500.0, 500.0);
		System.out.println("\n### Printing current trips for r1, r2 and r4");
		System.out.println(ridersController.fetchHistory(r1).getBody());
		System.out.println(ridersController.fetchHistory(r2).getBody());
		System.out.println(ridersController.fetchHistory(r4).getBody());

		assertThrows(RiderNotFoundException.class, () -> {
			ridersController.book("abcd", 0.0, 0.0, 500.0, 500.0);
		});

		assertThrows(RiderAlreadyExistException.class, () -> {
			ridersController.createRider("r1", "shjgf");
		});

		assertThrows(CabAlreadyExistException.class, () -> {
			cabsController.registerCab("c1", "skjhsfkj");
		});

		assertThrows(CabNotFoundException.class, () -> {
			cabsController.updateLocation("shss", 110.0, 110.0);
		});

		assertThrows(CabNotFoundException.class, () -> {
			cabsController.updateCabAvailability("shss", false);
		});

    }

}
