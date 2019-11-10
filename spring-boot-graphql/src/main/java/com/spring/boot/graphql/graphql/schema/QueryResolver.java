package com.spring.boot.graphql.graphql.schema;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.spring.boot.graphql.exception.AgencyNotFoundException;
import com.spring.boot.graphql.exception.TourNotFoundException;
import com.spring.boot.graphql.model.Agency;
import com.spring.boot.graphql.model.Tour;
import com.spring.boot.graphql.repository.AgencyRepository;
import com.spring.boot.graphql.repository.TourRepository;

import org.springframework.stereotype.Component;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    private final TourRepository tourRepository;

    private final AgencyRepository agencyRepository;



    // allTours: [Tour]
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }



    //tour(id: Long!): Tour
    public Tour getTour(Long id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new TourNotFoundException(id));
    }



    //allAgencies: [Agency]
    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }



    //agency(id: Long!): Agency
    public Agency getAgency(Long id) {
        return agencyRepository.findById(id)
                .orElseThrow(() -> new AgencyNotFoundException(id));
    }
}
