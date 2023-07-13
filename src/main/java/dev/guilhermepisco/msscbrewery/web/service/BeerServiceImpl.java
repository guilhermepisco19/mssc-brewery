package dev.guilhermepisco.msscbrewery.web.service;

import dev.guilhermepisco.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService{

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return new BeerDto(UUID.randomUUID(), "Galaxy Cat", "Pale Ale", 0L);
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return new BeerDto(UUID.randomUUID(), beerDto.beerName(), beerDto.beerStyle(), beerDto.upc());
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //todo impl - would add impl to update a beer
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        log.info("Deleting beer ...");
    }
}
