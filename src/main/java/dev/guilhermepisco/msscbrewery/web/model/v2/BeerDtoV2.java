package dev.guilhermepisco.msscbrewery.web.model.v2;

import java.util.UUID;

public record BeerDtoV2(UUID id, String beerName, BeerStyle beerStyle, Long upc) {
}
