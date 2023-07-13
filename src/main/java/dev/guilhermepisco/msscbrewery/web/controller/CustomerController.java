package dev.guilhermepisco.msscbrewery.web.controller;

import dev.guilhermepisco.msscbrewery.web.model.CustomerDto;
import dev.guilhermepisco.msscbrewery.web.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> findBeer(@PathVariable UUID customerId){

        return ResponseEntity
                .ok()
                .body(customerService.getCustomerById(customerId));

    }

    @PostMapping
    public ResponseEntity<Void> handlePost(@RequestBody CustomerDto customerDto){

        CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedDto.customerId()).toUri();

        return ResponseEntity
                .created(uri)
                .build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> handlePut(@PathVariable UUID customerId, @RequestBody CustomerDto customerDto){

        customerService.updateCustomer(customerId, customerDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID customerId){
        customerService.deleteCustomerById(customerId);
    }


}
