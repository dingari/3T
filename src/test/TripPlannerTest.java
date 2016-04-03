package test;

import main.TripCombo;
import main.TripPlanner;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

public class TripPlannerTest {

    @Test
    public void testPriceSum() {
        String depLoc = "KEF";
        String destLoc = "CPH";
        Date depTime = new Date();
        Date returnTime = new Date();
        boolean oneWay = false;
        int numPeople = 2;
        int priceLower = 50000;
        int priceHigher = 200000;
        int tourDuration = 3;

        TripPlanner tripPlanner = new TripPlanner(depLoc, destLoc, depTime, returnTime, numPeople, priceLower, priceHigher, tourDuration);

        List<TripCombo> combos = tripPlanner.suggestCombos();

        for(TripCombo combo: combos) {
            assertTrue(combo.getPrice() < priceHigher);
        }
    }
}
