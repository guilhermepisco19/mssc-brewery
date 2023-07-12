package dev.guilhermepisco.msscbrewery.web.controller;

import dev.guilhermepisco.msscbrewery.web.model.CustomerDto;
import dev.guilhermepisco.msscbrewery.web.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
