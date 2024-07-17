package com.hotel.occupancy.service;

import com.hotel.occupancy.exception.HotelOccupancyException;
import com.hotel.occupancy.model.OccupancyRequest;
import com.hotel.occupancy.model.OccupancyResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OccupancyServiceTest {
    private final OccupancyService service = new OccupancyService();

    @Test
    void testCalculateOccupancyCase1() {
        OccupancyRequest request = new OccupancyRequest();
        request.setPremiumRooms(3);
        request.setEconomyRooms(3);
        request.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        OccupancyResponse response = service.calculateOccupancy(request);

        assertEquals(3, response.getUsagePremium());
        assertEquals(738.0, response.getRevenuePremium());
        assertEquals(3, response.getUsageEconomy());
        assertEquals(167.99, response.getRevenueEconomy());
    }

    @Test
    void testCalculateOccupancyCase2() {
        OccupancyRequest request = new OccupancyRequest();
        request.setPremiumRooms(7);
        request.setEconomyRooms(5);
        request.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        OccupancyResponse response = service.calculateOccupancy(request);

        assertEquals(6, response.getUsagePremium());
        assertEquals(1054.0, response.getRevenuePremium());
        assertEquals(4, response.getUsageEconomy());
        assertEquals(189.99, response.getRevenueEconomy());
    }

    @Test
    void testCalculateOccupancyCase3() {
        OccupancyRequest request = new OccupancyRequest();
        request.setPremiumRooms(2);
        request.setEconomyRooms(7);
        request.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        OccupancyResponse response = service.calculateOccupancy(request);

        assertEquals(2, response.getUsagePremium());
        assertEquals(583.0, response.getRevenuePremium());
        assertEquals(4, response.getUsageEconomy());
        assertEquals(189.99, response.getRevenueEconomy());
    }

    @Test
    void testCalculateOccupancyInvalidRequest() {
        OccupancyRequest request = new OccupancyRequest();
        request.setPremiumRooms(-1);
        request.setEconomyRooms(5);
        request.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0));

        assertThrows(HotelOccupancyException.class, () -> service.calculateOccupancy(request));
    }
}
