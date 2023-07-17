package dev.guilhermepisco.msscbrewery.web.mapper;

import dev.guilhermepisco.msscbrewery.domain.Beer;
import dev.guilhermepisco.msscbrewery.web.model.v2.BeerDtoV2;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDtoV2 beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDtoV2 beerDto);
}
