package com.hotel.occupancy.service;

import com.hotel.occupancy.exception.HotelOccupancyException;
import com.hotel.occupancy.model.OccupancyRequest;
import com.hotel.occupancy.model.OccupancyResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OccupancyService {

    public OccupancyResponse calculateOccupancy(OccupancyRequest request) {
        validateRequest(request);

        List<Double> premiumGuests = filterGuestsByPrice(request.getPotentialGuests(), 100, true);
        List<Double> economyGuests = filterGuestsByPrice(request.getPotentialGuests(), 100, false);

        int usedPremiumRooms = 0;
        int usedEconomyRooms = 0;
        double premiumRevenue = 0;
        double economyRevenue = 0;

        for (double guest : premiumGuests) {
            if (usedPremiumRooms < request.getPremiumRooms()) {
                premiumRevenue += guest;
                usedPremiumRooms++;
            }
        }

        for (double guest : economyGuests) {
            if (usedEconomyRooms < request.getEconomyRooms()) {
                economyRevenue += guest;
                usedEconomyRooms++;
            } else if (usedPremiumRooms < request.getPremiumRooms()) {
                premiumRevenue += guest;
                usedPremiumRooms++;
            }
        }

        return new OccupancyResponse(usedPremiumRooms, premiumRevenue, usedEconomyRooms, economyRevenue);
    }

    private void validateRequest(OccupancyRequest request) {
        if (request.getPremiumRooms() < 0 || request.getEconomyRooms() < 0) {
            throw new HotelOccupancyException("Room numbers cannot be negative");
        }

        if (request.getPotentialGuests() == null || request.getPotentialGuests().isEmpty()) {
            throw new HotelOccupancyException("Potential guests list cannot be empty");
        }
    }

    private List<Double> filterGuestsByPrice(List<Double> guests, double threshold, boolean isPremium) {
        return guests.stream()
                .filter(price -> isPremium ? price >= threshold : price < threshold)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }
}
