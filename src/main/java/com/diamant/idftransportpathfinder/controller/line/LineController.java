package com.diamant.idftransportpathfinder.controller.line;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamant.idftransportpathfinder.dao.LineDAO;
import com.diamant.idftransportpathfinder.dtos.LineDTO;
import com.diamant.idftransportpathfinder.model.Line;
import com.diamant.idftransportpathfinder.model.line.RouteType;

@RestController
@RequestMapping("/api/lines")
public class LineController {
    @GetMapping("/routeType")
    public Set<LineDTO> loadLinesByType(@RequestParam String type) {
        List<Line> lines = LineDAO.findLineByType(RouteType.fromString(type));
        return lines.stream().map(LineDTO::new).collect(Collectors.toSet());
    }
}
