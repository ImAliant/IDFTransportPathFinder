package com.diamant.idftransportpathfinder.controller.line;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamant.idftransportpathfinder.dao.LineDAO;
import com.diamant.idftransportpathfinder.model.Line;
import com.diamant.idftransportpathfinder.model.Station;
import com.diamant.idftransportpathfinder.model.line.RouteType;

@RestController
@RequestMapping("/api/lines")
public class LineController {
    private List<Line> lines = new ArrayList<>();
    private static Set<Station> stations = new HashSet<>();

    private LineController() {}

    @PostMapping("/lines")
    public ResponseEntity<Void> setLinesByType(@RequestParam String type) {
        lines.clear();
        stations.clear();
        lines.addAll(LineDAO.findLineByType(RouteType.fromString(type)));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stations")
    public ResponseEntity<Void> setStationsByLine(@RequestParam String lineName) {
        stations.clear();
        for (Line line : lines) {
            if (line.getName().equals(lineName)) {
                stations.addAll(line.getStations());
                break;
            }
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lines")
    public List<Line> getLines() {
        return lines;
    }

    @GetMapping("/stations")
    public Set<Station> getStations() {
        return stations;
    }
}
