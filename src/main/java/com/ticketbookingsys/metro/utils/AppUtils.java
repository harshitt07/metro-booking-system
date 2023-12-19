package com.ticketbookingsys.metro.utils;

import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppUtils {

    private final StationRepository stationRepository;

    public Station findStation(String stationName) throws NotFoundException {
        log.info("inside {} utils", AppUtils.class);
        Optional<Station> station = stationRepository.findOne(Example.of(Station.builder().name(stationName).build()));
        return station.orElseThrow(() -> new NotFoundException(stationName + " doesn't exists"));
    }

}
