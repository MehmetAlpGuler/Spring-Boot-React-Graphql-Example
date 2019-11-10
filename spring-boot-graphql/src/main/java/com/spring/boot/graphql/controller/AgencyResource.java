package com.spring.boot.graphql.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.graphql.graphql.schema.MutationResolver;
import com.spring.boot.graphql.graphql.schema.QueryResolver;
import com.spring.boot.graphql.model.Agency;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/rest/agency")
public class AgencyResource {

    private final QueryResolver query;
    private final MutationResolver mutation;



    @GetMapping
    @ResponseBody
    public List<Agency> get() {
        return query.getAllAgencies();
    }



    @GetMapping(value = "{id}")
    @ResponseBody
    public Agency get(@PathVariable("id") Long id) {
        return query.getAgency(id);
    }



    @PostMapping
    @ResponseBody
    public void createAgency(String name, Double rating) {
        mutation.addAgency(name, rating);
    }
}