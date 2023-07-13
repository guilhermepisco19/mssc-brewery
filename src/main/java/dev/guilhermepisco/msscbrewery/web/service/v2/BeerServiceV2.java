package dev.guilhermepisco.msscbrewery.web.service.v2;

import dev.guilhermepisco.msscbrewery.web.model.BeerDto;
import dev.guilhermepisco.msscbrewery.web.model.v2.BeerDtoV2;

import java.util.UUID;

public interface BeerServiceV2 {

    BeerDtoV2 getBeerById(UUID beerId);

    BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);

    void updateBeer(UUID beerId, BeerDtoV2 beerDto);

    void deleteBeerById(UUID beerId);
}
