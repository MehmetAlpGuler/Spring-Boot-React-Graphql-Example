package com.spring.boot.graphql.repository;

import com.spring.boot.graphql.model.Agency;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgencyRepository extends JpaRepository<Agency, Long> {
    Optional<Agency> findById(Long id);
}