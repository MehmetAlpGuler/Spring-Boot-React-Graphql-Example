package com.spring.boot.graphql.repository;

import com.spring.boot.graphql.model.Tour;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {
    Optional<Tour> findById(Long id);
    Optional<Boolean> deleteById(Long id);
}
