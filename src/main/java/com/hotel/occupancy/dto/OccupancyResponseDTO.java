package com.hotel.occupancy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OccupancyResponseDTO {
    private int usagePremium;
    private double revenuePremium;
    private int usageEconomy;
    private double revenueEconomy;
}
