package dev.guilhermepisco.msscbrewery.web.model.v2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;
import java.util.UUID;

public record BeerDtoV2(
        @Null
        UUID id,
        @NotBlank
        String beerName,
        BeerStyle beerStyle,
        @Positive
        Long upc,
        OffsetDateTime createdDate,
        OffsetDateTime lastUpdatedDate) {
}
