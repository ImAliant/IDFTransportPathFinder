package com.diamant.idftransportpathfinder.controller;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
    private static final Set<Station> stations;

    static {
        stations = new HashSet<>(StationDAO.getAllStations());
    }

    private StationController() {}

    @GetMapping("/viewport")
    public Set<StationDTO> getStationsInView(
        @RequestParam double south,
        @RequestParam double north,
        @RequestParam double west,
        @RequestParam double east,
        @RequestParam int zoom
    ) {
        return stations.stream()
            .filter(station -> {
                double lat = station.getCoordinates().getLatitude();
                double lon = station.getCoordinates().getLongitude();
                return lat >= south && lat <= north &&
                       lon >= west && lon <= east;
            })
            .map(StationDTO::new)
            .collect(Collectors.toSet());
    }

    @GetMapping("/search")
    public List<StationDTO> searchStations(@RequestParam String name) {
        return stations.stream()
            .filter(station -> station.getName().toLowerCase().contains(name.toLowerCase()))
            .sorted(Comparator.comparing(Station::getName)
                    .thenComparing(s -> s.getLineKey().getRouteType())
                    .thenComparing(s -> s.getLineKey().getName()))
            .map(StationDTO::new)
            .collect(Collectors.toList());
    }
}
