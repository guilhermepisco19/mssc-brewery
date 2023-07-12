package dev.guilhermepisco.msscbrewery.web.service;

import dev.guilhermepisco.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomerById(UUID customerId);
}
