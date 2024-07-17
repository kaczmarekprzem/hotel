package com.hotel.occupancy.model;

import lombok.Data;

import java.util.List;

@Data
public class OccupancyRequest {
    private int premiumRooms;
    private int economyRooms;
    private List<Double> potentialGuests;
}