package com.ticketbookingsys.metro.utils;

import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AppUtils {
    @Autowired
    private StationRepository stationRepository;

    public Station findStation(String stationName) throws NotFoundException {
        Optional<Station> sourceStation = stationRepository.findOne(Example.of(Station.builder().name(stationName).build()));
        return sourceStation.orElseThrow(() -> new NotFoundException(stationName + " doesn't exists"));
    }

}
