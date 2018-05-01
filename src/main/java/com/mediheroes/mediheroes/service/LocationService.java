package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.Location;
import com.mediheroes.mediheroes.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository repository;

    public LocationService(LocationRepository locationRepository) {
        repository = locationRepository;
    }

    public Optional<Location> find(long id){
        return repository.findById(id);
    }

}
