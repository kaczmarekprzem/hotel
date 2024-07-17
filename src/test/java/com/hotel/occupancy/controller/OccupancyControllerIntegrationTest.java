package com.hotel.occupancy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.occupancy.dto.OccupancyRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OccupancyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCalculateOccupancyCase1() throws Exception {
        OccupancyRequestDTO requestDTO = new OccupancyRequestDTO();
        requestDTO.setPremiumRooms(3);
        requestDTO.setEconomyRooms(3);
        requestDTO.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        mockMvc.perform(post("/occupancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usagePremium").value(3))
                .andExpect(jsonPath("$.revenuePremium").value(738.0))
                .andExpect(jsonPath("$.usageEconomy").value(3))
                .andExpect(jsonPath("$.revenueEconomy").value(167.99));
    }

    @Test
    void testCalculateOccupancyCase2() throws Exception {
        OccupancyRequestDTO requestDTO = new OccupancyRequestDTO();
        requestDTO.setPremiumRooms(7);
        requestDTO.setEconomyRooms(5);
        requestDTO.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        mockMvc.perform(post("/occupancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usagePremium").value(6))
                .andExpect(jsonPath("$.revenuePremium").value(1054.0))
                .andExpect(jsonPath("$.usageEconomy").value(4))
                .andExpect(jsonPath("$.revenueEconomy").value(189.99));
    }

    @Test
    void testCalculateOccupancyCase3() throws Exception {
        OccupancyRequestDTO requestDTO = new OccupancyRequestDTO();
        requestDTO.setPremiumRooms(2);
        requestDTO.setEconomyRooms(7);
        requestDTO.setPotentialGuests(Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0));

        mockMvc.perform(post("/occupancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usagePremium").value(2))
                .andExpect(jsonPath("$.revenuePremium").value(583.0))
                .andExpect(jsonPath("$.usageEconomy").value(4))
                .andExpect(jsonPath("$.revenueEconomy").value(189.99));
    }
}
