package dev.guilhermepisco.msscbrewery.web.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record BeerDto(
        @Null
        UUID id,
        @NotBlank
        String beerName,
        @NotBlank
        String beerStyle,
        @Positive
        Long upc) {
}
