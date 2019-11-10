package com.spring.boot.graphql.graphql.input;

import com.spring.boot.graphql.enums.TourType;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateTourInput {

    private Long id;
    private String name;
    private String price;
    private String duration;
    private String description;
    private TourType type;
    private Long agency;

}