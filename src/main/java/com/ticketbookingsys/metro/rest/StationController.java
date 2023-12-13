package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.request.CreateStationRequest;
import com.ticketbookingsys.metro.service.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    @PostMapping
    public ResponseEntity<Station> addStation(@RequestBody @Valid CreateStationRequest createStationRequest) {
        log.info("inside {} controller", StationController.class);
        return ResponseEntity.ok().body(stationService.addStation(createStationRequest));
    }

    @GetMapping
    public ResponseEntity<List<Station>> getAllStation() {
        log.info("inside {} controller", StationController.class);
        return ResponseEntity.ok().body(stationService.getAllStation());
    }

    @DeleteMapping("/{stationName}")
    public void deleteStation(@PathVariable(name = "stationName") String stationName) throws Exception {
        log.info("inside {} controller", StationController.class);
        stationService.deleteStation(stationName);
    }

}
