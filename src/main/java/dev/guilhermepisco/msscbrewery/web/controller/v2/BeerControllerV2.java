package dev.guilhermepisco.msscbrewery.web.controller.v2;

import dev.guilhermepisco.msscbrewery.web.model.v2.BeerDtoV2;
import dev.guilhermepisco.msscbrewery.web.service.v2.BeerServiceV2;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/beer")
public class BeerControllerV2 {


    private final BeerServiceV2 beerService;

    public BeerControllerV2(BeerServiceV2 beerService){
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> findBeer(@PathVariable UUID beerId){

        return ResponseEntity
                .ok()
                .body(beerService.getBeerById(beerId));

    }

    @PostMapping
    public ResponseEntity<Void> handlePost(@Valid @RequestBody BeerDtoV2 beerDto){

        BeerDtoV2 savedDto = beerService.saveNewBeer(beerDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedDto.id()).toUri();

        return ResponseEntity
                .created(uri)
                .build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> handlePut(@PathVariable UUID beerId, @Valid @RequestBody BeerDtoV2 beerDto){

        beerService.updateBeer(beerId, beerDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID beerId){
        beerService.deleteBeerById(beerId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> validationErrorHandler(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body(getErrorsFromException(ex));
    }

    private List<String> getErrorsFromException(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(this::buildErrorMessage)
                .toList();
    }

    private String buildErrorMessage(FieldError fieldError){
        return fieldError.getField() + " : " + fieldError.getDefaultMessage();
    }

}
