package com.ticketbookingsys.metro.service;

import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.repository.StationRepository;
import com.ticketbookingsys.metro.request.CreateStationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public Station addStation(CreateStationRequest createStationRequest) {
        log.info("inside {} service", StationService.class);
        Station station = Station.builder().name(createStationRequest.getStationName()).price(createStationRequest.getPrice()).build();
        return stationRepository.save(station);
    }

    public List<Station> getAllStation() {
        log.info("inside {} service", StationService.class);
        return stationRepository.findAll();
    }

    public void deleteStation(String stationName) throws Exception {
        log.info("inside {} service", StationService.class);
        Optional<Station> station = stationRepository.findOne(Example.of(Station.builder()
                .name(stationName)
                .build()));
        station.ifPresentOrElse(stationRepository::delete, () -> {
            throw new NotFoundException(stationName + " station does not exist");
        });
    }

}
