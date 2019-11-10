package com.spring.boot.graphql.graphql.input;

import com.spring.boot.graphql.enums.TourType;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateTourInput {

    private String name;
    private String price;
    private String duration;
    private String description;
    private TourType type;
    private Long agency;
}
