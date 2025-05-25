package com.diamant.idftransportpathfinder.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamant.idftransportpathfinder.dao.StationDAO;
import com.diamant.idftransportpathfinder.dtos.StationDTO;
import com.diamant.idftransportpathfinder.model.Station;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private StationController() {}

    /* @GetMapping
    public Set<StationDTO> getStations() {
        Set<Station> stations = StationDAO.getAllStations();
        System.out.println(String.format("Found %d stations", stations.size()));

        Set<StationDTO> stationDTOs = new HashSet<>();
        
        for (int i = 0; i < 1000; i++) {
            Station station = stations.stream().skip(i).findFirst().orElse(null);
            if (station == null) {
                break;
            }
            StationDTO stationDTO = new StationDTO(station);
            stationDTOs.add(stationDTO);
        }

        return stationDTOs;
    } */
    @GetMapping("/viewport")
    public Set<StationDTO> getStationsInView(
        @RequestParam double south,
        @RequestParam double north,
        @RequestParam double west,
        @RequestParam double east,
        @RequestParam int zoom
    ) {
        return StationDAO.getAllStations().stream()
            .filter(station -> {
                double lat = station.getCoordinates().getLatitude();
                double lon = station.getCoordinates().getLongitude();
                return lat >= south && lat <= north &&
                       lon >= west && lon <= east;
            })
            .map(StationDTO::new)
            .collect(Collectors.toSet());
    }
}
