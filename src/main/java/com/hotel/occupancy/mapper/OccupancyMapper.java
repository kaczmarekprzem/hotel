package com.hotel.occupancy.mapper;

import com.hotel.occupancy.dto.OccupancyRequestDTO;
import com.hotel.occupancy.dto.OccupancyResponseDTO;
import com.hotel.occupancy.model.OccupancyRequest;
import com.hotel.occupancy.model.OccupancyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OccupancyMapper {

    OccupancyRequest toEntity(OccupancyRequestDTO dto);

    OccupancyResponseDTO toDTO(OccupancyResponse entity);
}
