package dev.guilhermepisco.msscbrewery.web.controller;

import dev.guilhermepisco.msscbrewery.web.model.BeerDto;
import dev.guilhermepisco.msscbrewery.web.service.BeerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService){
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> findBeer(@PathVariable UUID beerId){

        return ResponseEntity
                .ok()
                .body(beerService.getBeerById(beerId));

    }

}
