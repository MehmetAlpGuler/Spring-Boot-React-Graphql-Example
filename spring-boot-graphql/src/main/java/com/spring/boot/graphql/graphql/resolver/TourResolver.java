package com.spring.boot.graphql.graphql.resolver;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.spring.boot.graphql.exception.TourNotFoundException;
import com.spring.boot.graphql.model.Agency;
import com.spring.boot.graphql.model.Tour;
import com.spring.boot.graphql.repository.AgencyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TourResolver implements GraphQLResolver<Tour> {

    private final AgencyRepository agencyRepository;

    public Agency getAgency(Tour tour) {
        final Long tourId = tour.getAgency().getId();
        return agencyRepository.findById(tourId)
                .orElseThrow(() -> new TourNotFoundException(tourId));
    }
}
