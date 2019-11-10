package com.spring.boot.graphql.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.graphql.graphql.input.CreateTourInput;
import com.spring.boot.graphql.graphql.input.UpdateTourInput;
import com.spring.boot.graphql.graphql.schema.MutationResolver;
import com.spring.boot.graphql.graphql.schema.QueryResolver;
import com.spring.boot.graphql.model.Tour;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/rest/tour")
public class TourResource {

    private final QueryResolver query;
    private final MutationResolver mutation;

    @GetMapping
    @ResponseBody
    public List<Tour> get() {
        return query.getAllTours();
    }



    @GetMapping(value = "{id}")
    @ResponseBody
    public Tour get(@PathVariable("id") Long id) {
        return query.getTour(id);
    }



    @PostMapping
    @ResponseBody
    public Tour create(@RequestBody CreateTourInput tourInput) {
        return mutation.createTour(tourInput);
    }



    @PatchMapping
    @ResponseBody
    public Tour update(@RequestBody UpdateTourInput tourInput) {
        return mutation.updateTour(tourInput);
    }
}
