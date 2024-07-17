package com.hotel.occupancy.controller;

import com.hotel.occupancy.dto.OccupancyRequestDTO;
import com.hotel.occupancy.dto.OccupancyResponseDTO;
import com.hotel.occupancy.mapper.OccupancyMapper;
import com.hotel.occupancy.model.OccupancyRequest;
import com.hotel.occupancy.service.OccupancyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/occupancy")
@Tag(name = "Occupancy Management", description = "APIs for managing hotel room occupancy")
public class OccupancyController {

    private final OccupancyService occupancyService;
    private final OccupancyMapper mapper;

    @PostMapping
    @Operation(summary = "Calculate room occupancy and revenue")
    public OccupancyResponseDTO calculateOccupancy(@RequestBody OccupancyRequestDTO requestDTO) {
        OccupancyRequest request = mapper.toEntity(requestDTO);
        return mapper.toDTO(occupancyService.calculateOccupancy(request));
    }
}
