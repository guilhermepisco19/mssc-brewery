package dev.guilhermepisco.msscbrewery.web.model;


import java.util.UUID;

public record BeerDto(UUID id, String beerName, String beerStyle, Long upc) {
}
