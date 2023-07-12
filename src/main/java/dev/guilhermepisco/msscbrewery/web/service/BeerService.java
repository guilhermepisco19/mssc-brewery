package dev.guilhermepisco.msscbrewery.web.service;

import dev.guilhermepisco.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}
