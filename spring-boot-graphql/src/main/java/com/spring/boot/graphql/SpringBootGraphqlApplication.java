package com.spring.boot.graphql;

import com.spring.boot.graphql.enums.TourType;
import com.spring.boot.graphql.exception.GraphQLErrorAdapter;
import com.spring.boot.graphql.model.Agency;
import com.spring.boot.graphql.model.Tour;
import com.spring.boot.graphql.repository.AgencyRepository;
import com.spring.boot.graphql.repository.TourRepository;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
public class SpringBootGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGraphqlApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }



            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public CommandLineRunner run(TourRepository tourRepository, AgencyRepository agencyRepository) {
        return (args) -> {
            Agency lAgency = new Agency(1L, "AtoZTours", 5d);
            agencyRepository.save(lAgency);
            Stream.of(
                    new Tour(1L, "Trondheim", "233", "2",
                            "Trondheim Kunstmuseum is an art museum located in Trondheim in Sør-Trøndelag county, Norway.", TourType.ECONOMY, lAgency),
                    new Tour(2L, "Nidaros Cathedral", "100", "1",
                            "Nidaros Cathedral is a Church of Norway cathedral located in the city of Trondheim in Trøndelag county, Norway", TourType.COUPLE, lAgency),
                    new Tour(3L, "Nidelva", "343", "2",
                            "Nidelva is a river in Trøndelag county, Norway. The 30-kilometre long river travels through the municipalities of Trondheim and Klæbu.", TourType.ADVENTURE, lAgency))
                    .forEach(tourRepository::save);
        };
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo());
    }



    private ApiInfo generateApiInfo() {
        return new ApiInfo(
                "Tour Rest Service",
                "This service is to perform CRUD operations.",
                "Version 1.0 - mw",
                "urn:tos",
                new Contact("Mehmet Alp Guler", "https://https://github.com/MehmetAlpGuler", "mehmetalpguler@gmail.com"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
