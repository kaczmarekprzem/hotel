package com.hotel.occupancy.dto;

import lombok.Data;

import java.util.List;

@Data
public class OccupancyRequestDTO {
    private int premiumRooms;
    private int economyRooms;
    private List<Double> potentialGuests;
}
