package dev.guilhermepisco.msscbrewery.web.controller;

import dev.guilhermepisco.msscbrewery.web.model.BeerDto;
import dev.guilhermepisco.msscbrewery.web.service.BeerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Void> handlePost(@RequestBody BeerDto beerDto){

        BeerDto savedDto = beerService.saveNewBeer(beerDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedDto.id()).toUri();

        return ResponseEntity
                .created(uri)
                .build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> handlePut(@PathVariable UUID beerId, @RequestBody BeerDto beerDto){

        beerService.updateBeer(beerId, beerDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID beerId){
        beerService.deleteBeerById(beerId);
    }

}
