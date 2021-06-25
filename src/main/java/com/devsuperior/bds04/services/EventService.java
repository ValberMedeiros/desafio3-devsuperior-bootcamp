package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventService {

    private final EventRepository repository;
    private final CityRepository cityRepository;

    public EventService(EventRepository repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        var entity = new Event();
        Optional<City> city = cityRepository.findById(dto.getCityId());
        if (city.isPresent()) {
            entity.setCity(city.get());
        }
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity = repository.save(entity);
        return new EventDTO(entity);
    }

    public Page<EventDTO> findAllPaged(Pageable pageable) {
        Page<Event> list =  repository.findAll(pageable);
        return list
                .map(EventDTO::new);
    }
}
